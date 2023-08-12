package com.codezync.mediumretrofitmvvm.view

import android.content.Context
import android.content.LocusId
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.codezync.meadiummvvmexample.localDB.ArticleEntity
import com.codezync.meadiummvvmexample.model.ArticleResponse
import com.codezync.meadiummvvmexample.state_models.Resource
import com.codezync.mediumretrofitmvvm.Constants.Companion.QUERY_VALUE
import com.codezync.mediumretrofitmvvm.component.LoadingCircular
import com.codezync.mediumretrofitmvvm.viewModel.CreditCardViewModel
import com.codezync.toArticleEntityList


@Composable
fun CreditCardScreen(viewModel: CreditCardViewModel, context: Context) {

    val newsResponse =
        produceState<Resource<List<ArticleEntity>>>(initialValue = Resource.Loading()) {
            value = if (viewModel.getAllArticlesFromLocal().data.isNullOrEmpty()) {
                viewModel.getAllArticles(QUERY_VALUE, "39855b9e16bf4b21aabeaa39806004dd")
            } else {
                viewModel.getAllArticlesFromLocal()
            }

        }.value

    NewsResponseStateWrapper(newsResponse)

}


@Composable
fun showrecyclerView(articlesResponse: List<ArticleEntity>?) {
    Column {

        articlesResponse.let { it ->
            LazyColumn(modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(onPress = { /* Called when the gesture starts */ },
                    onDoubleTap = { /* Called on Double Tap */ },
                    onLongPress = {

                    },
                    onTap = { /* Called on Tap */ })
            }) {
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
            .padding(16.dp), elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
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
                        text = article.title, style = MaterialTheme.typography.h6
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (article != null) {
                if (article.author != null) {
                    Text(
                        text = article.author, style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}


@Composable
fun NewsResponseStateWrapper(
    pokemonInfo: Resource<List<ArticleEntity>>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when (pokemonInfo) {
        is Resource.Success -> {
            showrecyclerView(
                pokemonInfo.data
            )

        }

        is Resource.Error -> {
            Text(
                text = pokemonInfo.message!!, color = Color.Red, modifier = modifier
            )
        }

        is Resource.Loading -> {
            (LoadingCircular(
                modifier = Modifier.fillMaxWidth()
            ))
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


