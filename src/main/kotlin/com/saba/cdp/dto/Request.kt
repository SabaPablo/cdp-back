package com.saba.cdp.dto

import com.saba.cdp.models.OrderStatus

object Request {
    data class CreateBeer(val name: String, val description: String, val style: String,val brewery:String, val ibu:Int, val alcohol:Double,val price:Double,val url: String?)
    data class UpdateBeer(val id: Int, val name: String?, val description: String?, val style: String?,var url:String?, val brewery:String?, val ibu:Int?, val alcohol:Double?,val price:Double?)
    data class CreateOrder(val delivery:Boolean, val address:String?,val items: Set<CreateOrderItem>)
    data class CreateOrderItem(val beerId: Int, val cant:Int)
    data class UpdateOrder(val id: Int,val status: OrderStatus)
}
