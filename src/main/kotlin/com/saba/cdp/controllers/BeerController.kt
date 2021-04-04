package com.saba.cdp.controllers

import com.saba.cdp.daos.BeerDao
import com.saba.cdp.dto.Request
import com.saba.cdp.models.Beer
import kotlinx.coroutines.flow.toList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/beer")
class BeerController (
        private val beerDao: BeerDao
) {

    // GET /beer
    @CrossOrigin(origins = ["http://localhost:4200"])
    @GetMapping
    suspend fun getAllBeers(): List<Beer> {
        return beerDao.findAll().toList()
    }

    // GET /beer/search?name=tom
    @GetMapping("/search")
    suspend fun getBeerByName(@RequestParam name: String): List<Beer> {
        return beerDao.findByNameContains(name)
    }


    // POST /beer <Beer>
    @PostMapping
    suspend fun createBeer(@RequestBody request: Request.CreateBeer) {
        val beer = Beer(0,request.name, request.description, request.style,request.brewery,request.url?:"",request.ibu,request.alcohol,request.price)
        beerDao.save(beer)
    }

    // PUT /beer <data>
    @PutMapping
    suspend fun updateBeer(@RequestBody request: Request.UpdateBeer): ResponseEntity<Void> {
        val beer = beerDao.findById(request.id)
                ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        beer.name = request.name?:beer.name
        beer.description = request.description?:beer.description
        beer.style = request.style?:beer.style
        beer.brewery = request.brewery?:beer.brewery
        beer.url = request.url?:beer.url
        beer.ibu = request.ibu?:beer.ibu
        beer.alcohol = request.alcohol?:beer.alcohol
        beer.price = request.price?:beer.price
        beerDao.save(beer)
        return ResponseEntity(HttpStatus.OK)
    }


    // DELETE /beer/352
    @DeleteMapping("/{id}")
    suspend fun deleteBeer(@PathVariable id: Int): ResponseEntity<Void> {
        val beer = beerDao.findById(id)
                ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        beerDao.delete(beer)
        return ResponseEntity(HttpStatus.OK)
    }
}