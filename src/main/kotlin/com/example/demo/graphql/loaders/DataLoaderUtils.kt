package com.example.demo.graphql.loaders

import graphql.relay.Relay
import graphql.schema.DataFetchingEnvironment
import org.dataloader.DataLoader
import kotlin.reflect.KClass

fun <Loader : DataLoader<*, *>> getDataLoaderName(loaderClass: KClass<Loader>): String {
    return loaderClass.simpleName!!
}

inline fun <reified Loader : DataLoader<*, *>, K, V> getDataLoader(env: DataFetchingEnvironment): DataLoader<K, V> {
    return env.getDataLoader<K, V>(getDataLoaderName(Loader::class))
}
