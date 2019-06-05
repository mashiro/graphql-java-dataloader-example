package com.example.demo.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.coxautodev.graphql.tools.SchemaParser
import com.example.demo.graphql.loaders.getDataLoaderName
import graphql.GraphQL
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class GraphQLConfig {
    @Bean
    fun graphQL(resolvers: List<GraphQLResolver<*>>): GraphQL {
        val schema = SchemaParser.newParser()
                .file("graphql/schema.graphqls")
                .resolvers(resolvers)
                .build()
                .makeExecutableSchema()

        return GraphQL.newGraphQL(schema).build()
    }

    @Bean
    @Scope("prototype")
    fun dataLoaderRegistry(loaders: List<DataLoader<*, *>>): DataLoaderRegistry {
        return DataLoaderRegistry().also { registry ->
            loaders.forEach {
                registry.register(getDataLoaderName(it::class), it)
            }
        }
    }
}