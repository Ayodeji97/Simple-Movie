package com.example.movie.di.daggerhilt.datasource.remotesource

import com.example.movie.business.datasource.remote.remotesource.GetMoviesRemoteSource
import com.example.movie.business.datasource.remote.remotesource.GetMoviesRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteSourceModule {

    @Binds
    abstract fun provideMovieRemoteSource(
        getMoviesRemoteSourceImpl: GetMoviesRemoteSourceImpl
    ): GetMoviesRemoteSource
}
