package com.codezync.mediumretrofitmvvm.repository

import com.codezync.meadiummvvmexample.localDB.ArticleDao
import com.codezync.meadiummvvmexample.model.Article
import com.codezync.toArticleEntity


class ArticleEntityRepository(private val dao: ArticleDao) {

//    companion object {
//        fun provideArticleEntityRepository(orderDao: ArticleDao): ArticleEntityRepository {
//            return ArticleEntityRepository(dao = orderDao)
//        }
//    }

    suspend fun addListArticles(
        listArticles: List<Article>
    ) {

        val articleEntities = listArticles.map { article ->
            article.toArticleEntity()

        }
        dao.insertArticles(articleEntities)

    }

    suspend fun deleteArticle(articleId: Int): Int = dao.deleteThisArticleFromLocalDb(articleId)

    suspend fun getAllArticles() = dao.getAllArticles()


}