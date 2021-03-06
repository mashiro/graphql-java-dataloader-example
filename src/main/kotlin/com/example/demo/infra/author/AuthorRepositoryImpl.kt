package com.example.demo.infra.author

import com.example.demo.domain.author.Author
import com.example.demo.domain.author.AuthorRepository
import com.example.demo.graphql.types.ID
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class AuthorRepositoryImpl : AuthorRepository {
    companion object {
        private val logger = LoggerFactory.getLogger(AuthorRepositoryImpl::class.java)

        private val authors = listOf(
                AuthorEntity("1", "hoge"),
                AuthorEntity("2", "fuga")
        )
    }

    override fun get(id: String): Author? {
        logger.info("get: {}", id)
        return authors.find { it.id == id }?.let { convertEntity(it) }
    }

    override fun getAll(ids: List<ID>): List<Author> {
        logger.info("getAll: {}", ids)
        return authors.filter { ids.contains(it.id) }.map { convertEntity(it) }
    }

    private fun convertEntity(entity: AuthorEntity): Author {
        return Author(id = entity.id, name = entity.name)
    }
}