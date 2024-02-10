package com.givekesh.cafebazaar.assignment.data.di.module

import com.givekesh.cafebazaar.assignment.data.source.remote.repository.movies.MoviesRepository
import com.givekesh.cafebazaar.assignment.data.source.remote.repository.movies.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(impl: MoviesRepositoryImpl): MoviesRepository
}