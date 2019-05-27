package com.example.demo.domain.author

import com.example.demo.graphql.types.ID
import org.springframework.stereotype.Service

@Service
class AuthorService(
        private val authorRepository: AuthorRepository
) {
    fun get(id: String) = authorRepository.get(id)
    fun getAll(ids: List<ID>) = authorRepository.getAll(ids)
}