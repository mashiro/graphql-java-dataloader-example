package com.example.demo.domain.book

import com.example.demo.graphql.types.ID

interface BookRepository {
    fun get(id: String): Book?
    fun getAll(): List<Book>
    fun getAll(ids: List<ID>): List<Book>
    fun getAllByAuthorId(authorId: String): List<Book>
}