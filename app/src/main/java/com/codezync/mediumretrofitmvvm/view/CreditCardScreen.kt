package com.codezync.mediumretrofitmvvm.view

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.codezync.meadiummvvmexample.localDB.ArticleEntity
import com.codezync.meadiummvvmexample.model.Article
import com.codezync.meadiummvvmexample.model.ArticleResponse
import com.codezync.meadiummvvmexample.model.Source
import com.codezync.meadiummvvmexample.state_models.Resource
import com.codezync.meadiummvvmexample.state_models.ResourceState
import com.codezync.mediumretrofitmvvm.Constants.Companion.QUERY_VALUE
import com.codezync.mediumretrofitmvvm.Constants.DatabaseProvider.getDatabase
import com.codezync.mediumretrofitmvvm.component.LoadingCircular
import com.codezync.mediumretrofitmvvm.repository.ArticleEntityRepository
import com.codezync.mediumretrofitmvvm.viewModel.CreditCardViewModel
import com.codezync.mediumretrofitmvvm.viewModel.CreditCartViewModelFactory
import com.codezync.toArticleEntity


@Composable
fun CreditCardScreen(viewModel: CreditCardViewModel,context : Context) {
    val articlesResponse by viewModel.getAllArticlesResponseLiveData.observeAsState()
val isLocalStorageEmpty by viewModel.isLocalStorageEmpty.observeAsState()

        when (isLocalStorageEmpty?.state) {
            ResourceState.LOADING -> {
                LoadingCircular(
                    modifier = Modifier.fillMaxWidth(),
                )

            }

            ResourceState.SUCCESS -> {
                if (isLocalStorageEmpty?.data == true) {

                    LaunchedEffect(Unit) {
                        // viewModel.getAllArticles(QUERY_VALUE,"39855b9e16bf4b21aabeaa39806004dd")
                        viewModel.getAllArticles(QUERY_VALUE, "39855b9e16bf4b21aabeaa39806004dd")
                    }


                } else {
                    LaunchedEffect(Unit) {
                        viewModel.fetchDataFromLocal()
                    }
                }

            }

            ResourceState.ERROR -> {

            }


        }






//        if (articlesResponse == null) {
//            // Show loading indicator or placeholder
//            Text(text = "Loading...")
//        } else {
//            // Display the list of credit cards
//
//
//
//        }

        articlesResponse?.apply {

            when (state) {
                ResourceState.LOADING -> {
                    LoadingCircular(
                        modifier = Modifier.fillMaxWidth(),
                    )

                }
                ResourceState.ERROR -> {

                }

                ResourceState.SUCCESS ->{

                    showrecyclerView(articlesResponse!!.data,context)

                }


            }
        }
    }


@Composable
fun showrecyclerView(articlesResponse : List<ArticleEntity>?, context: Context){
    Column {

        articlesResponse.let { it ->
            LazyColumn(modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(
                    onPress = { /* Called when the gesture starts */ },
                    onDoubleTap = { /* Called on Double Tap */ },
                    onLongPress = { Toast.makeText(context,"long pressed",Toast.LENGTH_SHORT).show() },
                    onTap = { /* Called on Tap */ }
                )
            })
            {
                items(items = articlesResponse!!) {
                    CreditCardItem(it)
                }
            }
        }


    }


}


@Composable
fun CreditCardItem(article: ArticleEntity?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp), shape = RectangleShape, elevation = 4.dp
            ) {
                if (article != null) {
                    Image(painter = rememberImagePainter(data = article.urlToImage, builder = {
                        crossfade(true)
                    }), contentDescription = "Movie Poster")
                }
//                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")
            }
            if (article != null) {
                if (article.title != null) {
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.h6
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (article != null) {
                if (article.author != null) {
                    Text(
                        text = article.author,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

/*@Preview
@Composable
fun CreditCardItemPreview() {
    CreditCardItem(
        article = Article(
            "sfsf", "sfsfss", "sfsfdsfdsfds", "ersgsg",
            Source("gsgs", "sgsgs"),
            "yhhaasd", "oijljfs", "jlhhfjs"
        )
    )
}*/


