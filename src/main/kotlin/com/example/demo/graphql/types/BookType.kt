package com.example.demo.graphql.types

import com.example.demo.domain.book.Book

data class BookType(
        val id: ID,
        val name: String,
        val authorId: ID
) {
    companion object {
        fun fromDomain(book: Book): BookType {
            return BookType(
                    id = toGlobalId<BookType>(book.id),
                    name = book.name,
                    authorId = toGlobalId<AuthorType>(book.authorId)
            )
        }
    }
}
