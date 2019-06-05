package com.example.demo.graphql.loaders

import com.example.demo.domain.book.BookService
import com.example.demo.graphql.types.BookType
import com.example.demo.graphql.types.ID
import com.example.demo.graphql.types.fromGlobalId
import graphql.schema.DataFetchingEnvironment
import org.dataloader.BatchLoader
import org.dataloader.DataLoader
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture

@DataLoaderComponent
class BookDataLoader(
        private val bookService: BookService
) : DataLoader<ID, BookType>(BatchLoader { keys ->
    CompletableFuture.supplyAsync {
        val ids = keys.map { fromGlobalId(it).id }
        logger.info("batch load: {}", ids)

        val books = bookService.getAll(ids)
                .map { BookType.fromDomain(it) }
                .associate { Pair(it.id, it) }

        keys.map { books[it] }
    }
}) {
    companion object {
        private val logger = LoggerFactory.getLogger(AuthorDataLoader::class.java)

        fun getDataLoader(env: DataFetchingEnvironment) = getDataLoader<BookDataLoader, String, BookType>(env)
    }
}

