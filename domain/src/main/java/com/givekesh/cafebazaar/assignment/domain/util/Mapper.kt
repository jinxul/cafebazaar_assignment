package com.givekesh.cafebazaar.assignment.domain.util

internal interface ObjectToObjectMapper<T, R> {
    fun toObject(from: T): R
}

internal interface ListToListMapper<T, R> : ObjectToObjectMapper<T, R> {
    fun toList(from: List<T>): List<R> = from.map { toObject(it) }
}

