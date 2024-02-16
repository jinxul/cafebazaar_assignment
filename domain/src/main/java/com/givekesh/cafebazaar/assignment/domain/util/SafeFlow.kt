package com.givekesh.cafebazaar.assignment.domain.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal fun <DomainResponseType, ApiResponseType> safeFlow(
    apiCall: suspend () -> ApiResponseType,
    block: suspend FlowCollector<DataState<DomainResponseType>>.(apiResponse: ApiResponseType) -> Unit
): Flow<DataState<DomainResponseType>> = flow {
    emit(DataState.Loading)
    try {
        val result = apiCall.invoke()
        block.invoke(this, result)
    } catch (e: Exception) {
        val className = block.javaClass.name
            .substringAfterLast(".")
            .substringBefore("$")
        val methodName = block.javaClass.enclosingMethod?.name ?: "METHOD_NAME"
        Log.e(className, "$methodName: $e", e)
        emit(DataState.Failed("$e"))
    }
}.flowOn(Dispatchers.IO)

internal fun <DomainResponseType, ApiResponseType> safeChannelFlow(
    apiCall: suspend () -> ApiResponseType,
    block: suspend ProducerScope<DataState<DomainResponseType>>.(apiResponse: ApiResponseType) -> Unit
): Flow<DataState<DomainResponseType>> = channelFlow {
    trySend(DataState.Loading)
    try {
        val result = apiCall.invoke()
        block.invoke(this, result)
    } catch (e: Exception) {
        val className = block.javaClass.name
            .substringAfterLast(".")
            .substringBefore("$")
        val methodName = block.javaClass.enclosingMethod?.name ?: "METHOD_NAME"
        Log.e(className, "$methodName: $e", e)
        trySend(DataState.Failed("$e"))
    }
    awaitClose()
}.flowOn(Dispatchers.IO)