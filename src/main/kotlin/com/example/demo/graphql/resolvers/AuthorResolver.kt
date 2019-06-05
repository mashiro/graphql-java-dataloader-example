package com.example.demo.graphql.resolvers

import com.coxautodev.graphql.tools.GraphQLResolver
import com.example.demo.domain.book.BookService
import com.example.demo.graphql.types.AuthorType
import com.example.demo.graphql.types.BookType
import com.example.demo.graphql.types.fromGlobalId
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class AuthorResolver(
        private val bookService: BookService
) : GraphQLResolver<AuthorType> {
    fun books(author: AuthorType, first: Int = 10, after: String? = null, env: DataFetchingEnvironment): Connection<BookType> {
        val books = bookService.getAllByAuthorId(fromGlobalId(author.id).id).map { BookType.fromDomain(it) }
        return SimpleListConnection(books).get(env)
    }
}

