package com.givekesh.cafebazaar.assignment.data.util

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class TokenInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url
        val requestBuilder = originalRequest.newBuilder().url(url)
        val token =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMjQ3NzU0Zjg4ZTEwZWM1MDkwMTIyZjIxNDVkYTBiOCIsInN1YiI6IjYwNDA5YjMzZGI3MDU4MDAyM2UwOGUzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.HVB1bwdYZNNTdZBregP6jVG25Kxg5rXLho5sjYnEFE8"

        requestBuilder.addHeader(
            name = "Authorization",
            value = "Bearer $token"
        )

        return chain.proceed(requestBuilder.build())
    }
}