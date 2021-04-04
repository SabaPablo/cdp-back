package com.saba.cdp.daos

import com.saba.cdp.models.Purchase
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PurchaseDao : CoroutineCrudRepository<Purchase, Int> {

}

