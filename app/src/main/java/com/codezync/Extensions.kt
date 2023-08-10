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