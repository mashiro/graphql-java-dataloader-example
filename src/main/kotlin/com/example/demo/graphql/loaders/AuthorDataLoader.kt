package com.example.demo.graphql.loaders

import com.example.demo.graphql.types.AuthorType
import com.example.demo.domain.author.AuthorService
import com.example.demo.graphql.types.ID
import com.example.demo.graphql.types.fromGlobalId
import com.example.demo.graphql.types.toGlobalId
import graphql.schema.DataFetchingEnvironment
import org.dataloader.BatchLoader
import org.dataloader.DataLoader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class AuthorDataLoader(
        private val authorService: AuthorService
) : DataLoader<ID, AuthorType>(BatchLoader { keys ->
    CompletableFuture.supplyAsync {
        val ids = keys.map { fromGlobalId(it).id }
        logger.info("batch load: {}", ids)

        val authors = authorService.getAll(ids)
                .map { AuthorType.fromDomain(it) }
                .associate { Pair(it.id, it) }

        keys.map { authors[it] }
    }
}) {
    companion object {
        private val logger = LoggerFactory.getLogger(AuthorDataLoader::class.java)

        fun getDataLoader(env: DataFetchingEnvironment) = getDataLoader<AuthorDataLoader, String, AuthorType>(env)
    }
}