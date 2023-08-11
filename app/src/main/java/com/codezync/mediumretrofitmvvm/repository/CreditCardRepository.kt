package com.codezync.meadiummvvmexample.repository

import com.codezync.meadiummvvmexample.model.ArticleResponse
import com.codezync.meadiummvvmexample.model.CreditCardResponse
import com.codezync.meadiummvvmexample.service.RetrofitInstance
import com.codezync.meadiummvvmexample.state_models.Resource

class CreditCardRepository {

    private val creditCardService = RetrofitInstance.creditCardService

    //   suspend fun getAllArticles(query : String,apiKey : String): ArticleResponse = creditCardService.getAllArticles(query,apiKey)

    suspend fun getAllArticles(query: String, apiKey: String): Resource<ArticleResponse> {

        val response = try {
            creditCardService.getAllArticles(query, apiKey)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

}