package com.example.demo.domain.book

import com.example.demo.graphql.types.ID
import org.springframework.stereotype.Service

@Service
class BookService(
        private val bookRepository: BookRepository
) {
    fun get(id: String) = bookRepository.get(id)
    fun getAll() = bookRepository.getAll()
    fun getAll(ids: List<ID>) = bookRepository.getAll(ids)
}