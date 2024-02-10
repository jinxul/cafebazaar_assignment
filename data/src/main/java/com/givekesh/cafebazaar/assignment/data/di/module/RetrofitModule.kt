package com.givekesh.cafebazaar.assignment.data.di.module

import com.givekesh.cafebazaar.assignment.data.BuildConfig
import com.givekesh.cafebazaar.assignment.data.util.TimeoutInterceptor
import com.givekesh.cafebazaar.assignment.data.util.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    @Singleton
    @Provides
    fun provideTokenInterceptor(): TokenInterceptor = TokenInterceptor()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun provideOkhttp(
        loggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor,
        timeoutInterceptor: TimeoutInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .addInterceptor(tokenInterceptor)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(timeoutInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideDefaultRetrofit(
        factory: MoshiConverterFactory,
        client: OkHttpClient
    ): Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .client(client)
        .addConverterFactory(factory)
}