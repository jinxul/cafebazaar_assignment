package com.givekesh.cafebazaar.assignment.data.util

import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class TimeoutInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val timeout = 30
        return chain.withConnectTimeout(timeout, TimeUnit.SECONDS)
            .withReadTimeout(timeout, TimeUnit.SECONDS)
            .withWriteTimeout(timeout, TimeUnit.SECONDS)
            .proceed(request)
    }
}