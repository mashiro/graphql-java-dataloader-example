package com.example.demo.graphql.resolvers

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.demo.graphql.loaders.AuthorDataLoader
import com.example.demo.graphql.types.AuthorType
import com.example.demo.graphql.types.BookType
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class BookResolver : GraphQLResolver<BookType> {
    fun author(book: BookType, env: DataFetchingEnvironment): CompletableFuture<AuthorType> {
        val loader = AuthorDataLoader.getDataLoader(env)
        return loader.load(book.authorId)
    }
}

