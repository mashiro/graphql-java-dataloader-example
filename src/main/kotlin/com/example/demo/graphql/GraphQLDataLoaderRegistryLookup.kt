package com.example.demo.graphql

import org.dataloader.DataLoaderRegistry
import org.springframework.beans.factory.annotation.Lookup
import org.springframework.stereotype.Component

@Component
class GraphQLDataLoaderRegistryLookup {
    @Lookup
    fun dataLoaderRegistry(): DataLoaderRegistry {
        // dummy
        return DataLoaderRegistry()
    }
}