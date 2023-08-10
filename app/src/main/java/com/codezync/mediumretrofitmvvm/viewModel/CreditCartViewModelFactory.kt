package com.codezync.mediumretrofitmvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codezync.mediumretrofitmvvm.repository.ArticleEntityRepository

class CreditCartViewModelFactory(private val repository: ArticleEntityRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreditCardViewModel::class.java)) {
            return CreditCardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}