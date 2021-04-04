package com.saba.cdp.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("order_item")
data class OrderItem(@Id val id:Int,
                     val purchase:Int,
                     val beer: Int,
                     val cant:Int,
                     val price:Double)
