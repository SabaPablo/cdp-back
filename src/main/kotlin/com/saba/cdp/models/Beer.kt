package com.saba.cdp.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Beer(
                @Id var id:Int = 0,
                var name: String = "",
                var description: String= "",
                var style:String = "",
                var brewery:String = "",
                var url:String = "",
                var ibu:Int=0,
                var alcohol: Double = 0.0,
                var price: Double=0.0)
