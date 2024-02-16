package com.givekesh.cafebazaar.assignment.data.di.module

import com.givekesh.cafebazaar.assignment.data.source.remote.api.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkApiModule {
    @Singleton
    @Provides
    fun provideMoviesApi(
        retrofit: Retrofit.Builder
    ): MoviesApi = retrofit.build().create(MoviesApi::class.java)
}