package com.example.demo.infra.book

import com.example.demo.domain.book.Book
import com.example.demo.domain.book.BookRepository
import com.example.demo.graphql.types.ID
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl : BookRepository {
    companion object {
        private val logger = LoggerFactory.getLogger(BookRepositoryImpl::class.java)

        private val books = listOf(
                BookEntity("1", "foo", "1"),
                BookEntity("2", "bar", "2"),
                BookEntity("3", "buzz", "2")
        )
    }

    override fun get(id: String): Book? {
        logger.info("get: {}", id)
        return books.find { it.id == id }?.let { convertEntity(it) }
    }

    override fun getAll(): List<Book> {
        logger.info("getAll")
        return books.map { convertEntity(it) }
    }

    override fun getAll(ids: List<ID>): List<Book> {
        logger.info("getAll: {}", ids)
        return books.filter { ids.contains(it.id) }.map { convertEntity(it) }
    }

    override fun getAllByAuthorId(authorId: String): List<Book> {
        logger.info("getAllByAuthorId: {}", authorId)
        return books.filter { it.authorId == authorId }.map { convertEntity(it) }
    }

    private fun convertEntity(entity: BookEntity): Book {
        return Book(id = entity.id, name = entity.name, authorId = entity.authorId)
    }
}