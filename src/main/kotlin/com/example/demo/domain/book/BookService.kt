package com.example.demo.domain.book

import org.springframework.stereotype.Service

@Service
class BookService(
        private val bookRepository: BookRepository
) {
    fun get(id: String) = bookRepository.get(id)
    fun getAll() = bookRepository.getAll()
    fun getAll(ids: List<String>) = bookRepository.getAll(ids)
    fun getAllByAuthorId(authorId: String) = bookRepository.getAllByAuthorId(authorId)
}