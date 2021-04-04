package com.saba.cdp.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Purchase(@Id val id: Int, var delivery:Boolean, var address:String?,var status:OrderStatus)