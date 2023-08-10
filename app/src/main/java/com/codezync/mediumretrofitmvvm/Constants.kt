package com.codezync.mediumretrofitmvvm

import android.content.Context
import androidx.room.Room
import com.codezync.meadiummvvmexample.localDB.ArticleDataBase

class Constants {

    companion object {
        val BUNDLE_ARTICLE = "article"
        val QUERY_VALUE = "bitcoin"
    }


    object DatabaseProvider {
        private var instance: ArticleDataBase? = null

        fun getDatabase(context: Context): ArticleDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ArticleDataBase {
            return Room.databaseBuilder(
                context,
                ArticleDataBase::class.java,
                "your_database_name"
            ).build()
        }
    }
}