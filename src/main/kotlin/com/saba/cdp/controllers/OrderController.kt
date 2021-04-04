package com.saba.cdp.controllers

import com.saba.cdp.daos.BeerDao
import com.saba.cdp.daos.OrderItemDao
import com.saba.cdp.daos.PurchaseDao
import com.saba.cdp.dto.Request
import com.saba.cdp.models.Beer
import com.saba.cdp.models.OrderItem
import com.saba.cdp.models.OrderStatus
import com.saba.cdp.models.Purchase
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.flow.toList
import org.springframework.core.annotation.Order
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController (
        private val orderDao: PurchaseDao,
        private val itemDao: OrderItemDao,
        private val beerDao: BeerDao
) {

    // GET /beer
    @GetMapping
    suspend fun getAllOrders(): List<Purchase> {
        return orderDao.findAll().toList()
    }

    @GetMapping("items/{id}")
    suspend fun getItems(@PathVariable id: Int): ResponseEntity<List<OrderItem>> {
        orderDao.findById(id)
                ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(itemDao.findByOrderId(id),HttpStatus.OK)

    }

    // POST /purchase <Beer>
    @PostMapping
    @Transactional
    @Modifying
    suspend fun createOrder(@RequestBody request: Request.CreateOrder): ResponseEntity<List<OrderItem>>  {
        val order = Purchase(0,request.delivery,request.address, OrderStatus.CREATED)
        val orderSaved = orderDao.save(order)
        val items = mutableListOf<OrderItem>()
        request.items.forEach {requestItem ->
            val beer = beerDao.findById(requestItem.beerId)
                    ?: return ResponseEntity(HttpStatus.NOT_FOUND)
            val item = OrderItem(0,orderSaved.id,beer.id,requestItem.cant,beer.price*requestItem.cant)
            items.add(item)
            itemDao.save(item)

        }
        return ResponseEntity(items,HttpStatus.OK)
    }

    // PUT /beer <data>
    @PutMapping
    suspend fun updateOrder(@RequestBody request: Request.UpdateOrder): ResponseEntity<Void> {
        val order = orderDao.findById(request.id)
                ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        order.status = request.status
        orderDao.save(order)
        return ResponseEntity(HttpStatus.OK)
    }

}