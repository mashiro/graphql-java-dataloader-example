package com.example.demo.domain.author

import com.example.demo.graphql.types.ID

interface AuthorRepository {
    fun get(id: String): Author?
    fun getAll(ids: List<ID>): List<Author>
}