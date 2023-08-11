package com.codezync

import com.codezync.meadiummvvmexample.localDB.ArticleEntity
import com.codezync.meadiummvvmexample.model.Article

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        sourceId = this.source.id,
        sourceName = this.source.name,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}

fun List<Article>.toArticleEntityList(): List<ArticleEntity> {
    var list = mutableListOf<ArticleEntity>()
    this.forEach {
        it
        list.add(
            ArticleEntity(
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                sourceId = it.source.id,
                sourceName = it.source.name,
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage
            )
        )


    }

    return list
}