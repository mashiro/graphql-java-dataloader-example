package com.example.demo.graphql

import graphql.ExecutionInput
import graphql.ExecutionResult
import graphql.GraphQL
import graphql.spring.web.reactive.GraphQLInvocation
import graphql.spring.web.reactive.GraphQLInvocationData
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GraphQLInvocation(
        private val graphQL: GraphQL,
        private val dataLoaderRegistryLookup: GraphQLDataLoaderRegistryLookup
) : GraphQLInvocation {
    override fun invoke(invocationData: GraphQLInvocationData, webRequest: ServerWebExchange): Mono<ExecutionResult> {
        val executionInput = ExecutionInput.newExecutionInput()
                .query(invocationData.query)
                .operationName(invocationData.operationName)
                .variables(invocationData.variables)
                .dataLoaderRegistry(dataLoaderRegistryLookup.dataLoaderRegistry())
                .build()

        return Mono.fromCompletionStage(graphQL.executeAsync(executionInput))
    }
}