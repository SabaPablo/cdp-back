package com.saba.cdp.daos

import com.saba.cdp.models.Beer
import com.saba.cdp.models.OrderItem
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface OrderItemDao : CoroutineCrudRepository<OrderItem, Int> {

    // This is how to implement a custom query
    @Query("SELECT * FROM order_item WHERE order_id = orderId")
    suspend fun findByOrderId(OrderId: Int): List<OrderItem>

}