package com.codezync.mediumretrofitmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.codezync.mediumretrofitmvvm.repository.ArticleEntityRepository
import com.codezync.mediumretrofitmvvm.ui.theme.MediumRetrofitMVVMTheme
import com.codezync.mediumretrofitmvvm.view.CreditCardScreen
import com.codezync.mediumretrofitmvvm.viewModel.CreditCardViewModel
import com.codezync.mediumretrofitmvvm.viewModel.CreditCartViewModelFactory

class MainActivity : ComponentActivity() {
    // private val viewModel: CreditCardViewModel by viewModels()

    private lateinit var creditCardViewModel: CreditCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = ArticleEntityRepository(
            Constants.DatabaseProvider.getDatabase(this).provideArticleEntityDao()
        )
        creditCardViewModel = ViewModelProvider(
            this,
            CreditCartViewModelFactory(repository)
        ).get(CreditCardViewModel::class.java)
       // creditCardViewModel.isLocalStorageEmpty()
        setContent {
            MediumRetrofitMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreditCardScreen(creditCardViewModel,this)
                }
            }
        }
    }
}

