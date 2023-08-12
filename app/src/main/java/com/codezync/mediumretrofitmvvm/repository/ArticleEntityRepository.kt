package com.codezync.mediumretrofitmvvm.repository

import com.codezync.meadiummvvmexample.localDB.ArticleDao
import com.codezync.meadiummvvmexample.localDB.ArticleEntity
import com.codezync.meadiummvvmexample.model.Article
import com.codezync.meadiummvvmexample.state_models.Resource
import com.codezync.toArticleEntity


class ArticleEntityRepository(private val dao: ArticleDao) {

    suspend fun addListArticles(
        listArticles: List<ArticleEntity>
    ) {

        dao.insertArticles(listArticles)

    }

    suspend fun deleteArticle(articleId: Int): Int = dao.deleteThisArticleFromLocalDb(articleId)

    suspend fun getAllArticles(): Resource<List<ArticleEntity>> {

        val result = try {
            dao.getAllArticles()

        } catch (e: Exception) {
            return Resource.Error("Something went wrong")
        }

        return Resource.Success(result)


    }


}