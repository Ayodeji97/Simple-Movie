package com.example.movie.di.daggerhilt.repository

import com.engie.eea_tech_interview.business.repository.GetMoviesRepository
import com.engie.eea_tech_interview.business.repository.GetMoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetMovieRepositoryModule {

    @Binds
    abstract fun provideMovieRepository(
        getMoviesRepositoryImpl: GetMoviesRepositoryImpl
    ): GetMoviesRepository
}
