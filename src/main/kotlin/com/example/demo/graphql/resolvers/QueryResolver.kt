package com.example.demo.graphql.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.demo.graphql.types.BookType
import com.example.demo.domain.book.BookService
import com.example.demo.graphql.types.AuthorType
import com.example.demo.graphql.types.toGlobalId
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class QueryResolver(
        private val bookService: BookService
) : GraphQLQueryResolver {
    fun books(first: Int = 10, after: String? = null, env: DataFetchingEnvironment): Connection<BookType> {
        val books = bookService.getAll().map { BookType.fromDomain(it) }
        return SimpleListConnection(books).get(env)
    }
}