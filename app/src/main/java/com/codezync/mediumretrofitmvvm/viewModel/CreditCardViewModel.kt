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

    suspend fun getAllArticles(query: String, apiKey: String) =
        repository.getAllArticles(query, apiKey).also {

            it.data?.let { it1 -> articleRepository.addListArticles(it1) }
        }

    suspend fun getAllArticlesFromLocal() = articleRepository.getAllArticles()


}