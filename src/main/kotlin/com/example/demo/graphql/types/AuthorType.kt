package com.example.demo.graphql.types

import com.example.demo.domain.author.Author

data class AuthorType(
        val id: ID,
        val name: String
) {
    companion object {
        fun fromDomain(author: Author): AuthorType {
            return AuthorType(
                    id = toGlobalId<AuthorType>(author.id),
                    name = author.name
            )
        }
    }
}