package com.givekesh.cafebazaar.assignment.data.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.givekesh.cafebazaar.assignment.data.source.local.MoviesDao
import com.givekesh.cafebazaar.assignment.data.source.local.MoviesDatabase
import com.givekesh.cafebazaar.assignment.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RoomModule {
    @Singleton
    @Provides
    fun provideDatabaseBuilder(
        @ApplicationContext context: Context
    ): RoomDatabase.Builder<MoviesDatabase> = Room.databaseBuilder(
        context,
        MoviesDatabase::class.java,
        Constants.DATABASE_NAME,
    )

    @Singleton
    @Provides
    fun provideMoviesDatabase(
        databaseBuilder: RoomDatabase.Builder<MoviesDatabase>
    ): MoviesDatabase = databaseBuilder.fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideMoviesDao(db: MoviesDatabase): MoviesDao = db.moviesDao()
}