package com.givekesh.cafebazaar.assignment.domain.di.module

import com.givekesh.cafebazaar.assignment.domain.use_case.movie.MoviesUseCase
import com.givekesh.cafebazaar.assignment.domain.use_case.movie.MoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun bindMoviesUseCase(impl: MoviesUseCaseImpl): MoviesUseCase
}