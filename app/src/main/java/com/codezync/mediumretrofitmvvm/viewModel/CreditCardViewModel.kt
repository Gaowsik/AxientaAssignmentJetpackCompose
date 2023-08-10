package com.codezync.mediumretrofitmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codezync.meadiummvvmexample.localDB.ArticleDao
import com.codezync.meadiummvvmexample.localDB.ArticleEntity
import com.codezync.meadiummvvmexample.model.Article
import com.codezync.meadiummvvmexample.model.ArticleResponse
import com.codezync.meadiummvvmexample.model.CreditCard
import com.codezync.meadiummvvmexample.model.CreditCardResponse
import com.codezync.meadiummvvmexample.repository.CreditCardRepository
import com.codezync.meadiummvvmexample.state_models.*
import com.codezync.mediumretrofitmvvm.Constants
import com.codezync.mediumretrofitmvvm.repository.ArticleEntityRepository
import kotlinx.coroutines.launch

class CreditCardViewModel(private val articleRepository: ArticleEntityRepository) : ViewModel() {
    private val repository = CreditCardRepository()



/*    private val _creditCards = MutableLiveData<CreditCardResponse>()
    val creditCards: LiveData<CreditCardResponse> = _creditCards

    private val _articleLiveData = MutableLiveData<List<Article>>()
    val articleLiveData: LiveData<List<Article>> = _articleLiveData

    fun fetchCreditCards() {
        viewModelScope.launch {
            try {
                val cards = repository.getCreditCards()
                _creditCards.value = cards
            } catch (e: Exception) {
                // Handle error
            }
        }
    }


   fun getAllArticles(query : String,apiKey : String) {
        viewModelScope.launch {
            try {
                val articles = repository.getAllArticles(query,apiKey)
                _articleLiveData.value = articles.articles
                articleRepository.addListArticles(articles.articles)
            } catch (e: Exception) {
                // Handle error
                Log.d("error","error")
            }
        }
    }*/


    var getAllArticlesResponseLiveData: MutableLiveData<Resource<List<ArticleEntity>>> =
        MutableLiveData<Resource<List<ArticleEntity>>>()

    var isDataDeletedLiveData: MutableLiveData<Resource<Boolean>> =
        MutableLiveData<Resource<Boolean>>()

    var isLocalStorageEmpty: MutableLiveData<Resource<Boolean>> =
        MutableLiveData<Resource<Boolean>>()


    fun getAllArticles(query: String, apiKey: String) = viewModelScope.launch {
        getAllArticlesResponseLiveData.setLoading()
        try {
            val pDriverProfileResponse =
                repository.getAllArticles(query, apiKey)
            articleRepository.addListArticles(pDriverProfileResponse.articles)
            val articleDataFromLocal = articleRepository.getAllArticles()
            getAllArticlesResponseLiveData.setSuccess(
                data = articleDataFromLocal,
                message = null
            )
        } catch (e: Exception) {
            getAllArticlesResponseLiveData.setError(
                Resource(
                    data = e?.message,
                    message = "oops something went wrong",
                    state = ResourceState.ERROR
                )
            )
            e.printStackTrace()
            return@launch
        }
    }


    fun fetchDataFromLocal() = viewModelScope.launch {
        getAllArticlesResponseLiveData.setLoading()
        try {
            val articleDataFromLocal = articleRepository.getAllArticles()
            getAllArticlesResponseLiveData.setSuccess(
                data = articleDataFromLocal,
                message = null
            )
        } catch (e: Exception) {
            getAllArticlesResponseLiveData.setError(
                Resource(
                    data = e?.message,
                    message = "oops something went wrong",
                    state = ResourceState.ERROR
                )
            )
            e.printStackTrace()
            return@launch
        }
    }

    fun isLocalStorageEmpty() = viewModelScope.launch {
        isLocalStorageEmpty.setLoading()
        try {
            val articleDataFromLocal = articleRepository.getAllArticles()
            isLocalStorageEmpty.setSuccess(
                data = articleDataFromLocal.isNullOrEmpty(),
                message = null
            )
        } catch (e: Exception) {
            isLocalStorageEmpty.setError(
                Resource(
                    data = e?.message,
                    message = "oops something went wrong",
                    state = ResourceState.ERROR
                )
            )
            e.printStackTrace()
            return@launch
        }


    }


    fun deleteDataFromDB(article: ArticleEntity) = viewModelScope.launch {
        isDataDeletedLiveData.setLoading()
        try {
            articleRepository.deleteArticle(article.id)
            isDataDeletedLiveData.setSuccess(true, "")
        } catch (e: Exception) {
            isDataDeletedLiveData.setError(
                Resource(
                    data = e?.message,
                    message = "oops something went wrong",
                    state = ResourceState.ERROR
                )
            )
            e.printStackTrace()
            return@launch
        }


    }



}