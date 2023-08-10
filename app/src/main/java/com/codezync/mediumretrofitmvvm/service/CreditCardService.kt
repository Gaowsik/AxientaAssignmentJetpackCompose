package com.codezync.meadiummvvmexample.service

import com.codezync.meadiummvvmexample.model.ArticleResponse
import com.codezync.meadiummvvmexample.model.CreditCard
import com.codezync.meadiummvvmexample.model.CreditCardResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CreditCardService {
    @GET("credit_cards")
    suspend fun getCreditCards(): CreditCardResponse


    @GET("everything")
    suspend fun getAllArticles(@Query("q") query: String,
                               @Query("apiKey") apiKey: String): ArticleResponse
}