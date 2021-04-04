package com.saba.cdp.daos

import com.saba.cdp.models.Beer
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BeerDao : CoroutineCrudRepository<Beer, Int> {

    // SELECT * FROM person WHERE name LIKE '%tom%'
    suspend fun findByNameContains(name: String): List<Beer>

    // SELECT * FROM person WHERE age >= 42
    //suspend fun findByAgeGreaterThanEqual(age: Int): List<Beer>

    // This is how to implement a custom query
    @Query("SELECT * FROM beer WHERE name LIKE aName")
    suspend fun findByAName(aName: String): List<Beer>
}