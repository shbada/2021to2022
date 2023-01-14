package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(): Nothing {
    throw IllegalArgumentException()
}

// this : CrudRepository
fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id) ?: fail()
}