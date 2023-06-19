package com.example.movie.business.mapper.remotemapper

import com.example.movie.business.util.DummyData
import com.example.movie.business.utils.mapper.remotemapper.MovieDtoMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRemoteMapperTest {

    private lateinit var movieDtoMapper: MovieDtoMapper

    @Before
    fun setup() {
        movieDtoMapper = MovieDtoMapper()
    }

    @Test
    fun `map remote data against local data returns data are correctly mapped`() = runTest {
        val movieDto = DummyData.movieDto
        val mapEntity = movieDtoMapper.transformToEntity(listOf(movieDto))
        assertThat(mapEntity.first().title).isEqualTo(movieDto.title)
        assertThat(mapEntity.first().id).isEqualTo(movieDto.id)
        assertThat(mapEntity.first().overview).isEqualTo(movieDto.overview)
    }

    @Test
    fun `map remote data against local data returns data are invalidly mapped`() = runTest {
        val movieDto = DummyData.movieDto
        val mapEntity = movieDtoMapper.transformToEntity(listOf(movieDto))
        assertThat(DummyData.movieDto2.id).isNotEqualTo(mapEntity.first().id)
        assertThat(DummyData.movieDto2.title).isNotEqualTo(mapEntity.first().title)
        assertThat(DummyData.movieDto2.overview).isNotEqualTo(mapEntity.first().overview)
    }
}
