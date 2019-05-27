package com.example.demo.graphql.types

import graphql.relay.Relay

typealias ID = String

inline fun <reified T> toGlobalId(id: String): ID {
    return Relay().toGlobalId(T::class.simpleName, id)
}

fun fromGlobalId(globalId: ID): Relay.ResolvedGlobalId {
    return Relay().fromGlobalId(globalId)
}

