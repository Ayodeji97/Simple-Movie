package com.example.movie.di.daggerhilt.datasource.cachesource

import android.app.Application
import androidx.room.Room
import com.example.movie.business.datasource.cache.MovieDatabase
import com.example.movie.business.datasource.cache.dao.MovieDao
import com.example.movie.business.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheSourceModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): MovieDatabase =
        Room
            .databaseBuilder(app, MovieDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao =
        movieDatabase.movieDao()
}
