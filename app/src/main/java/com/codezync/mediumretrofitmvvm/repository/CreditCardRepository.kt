package com.codezync.meadiummvvmexample.repository

import com.codezync.meadiummvvmexample.model.ArticleResponse
import com.codezync.meadiummvvmexample.model.CreditCardResponse
import com.codezync.meadiummvvmexample.service.RetrofitInstance

class CreditCardRepository {

    private val creditCardService = RetrofitInstance.creditCardService

    suspend fun getCreditCards(): CreditCardResponse {
        return creditCardService.getCreditCards()
    }

    suspend fun getAllArticles(query : String,apiKey : String): ArticleResponse = creditCardService.getAllArticles(query,apiKey)

}