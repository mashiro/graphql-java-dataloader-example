package com.example.demo.graphql.loaders

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
@Scope("prototype")
annotation class DataLoaderComponent
