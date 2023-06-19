package com.example.movie.business.mapper.cachemapper

import com.example.movie.business.util.DummyData
import com.example.movie.business.utils.mapper.cachemapper.MovieCacheMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieCacheMapperTest {

    private lateinit var movieCacheMapper: MovieCacheMapper

    @Before
    fun setup() {
        movieCacheMapper = MovieCacheMapper()
    }

    @Test
    fun `map local data against domain data returns data are correctly mapped`() = runTest {
        val movieEntity = DummyData.movieEntity
        val mappedData = movieCacheMapper.transformToDomain(listOf(movieEntity))
        assertThat(mappedData.first().id).isEqualTo(movieEntity.id)
        assertThat(mappedData.first().title).isEqualTo(movieEntity.title)
        assertThat(mappedData.first().overview).isEqualTo(movieEntity.overview)
    }

    @Test
    fun `map local data against domain data returns data are incorrectly mapped`() = runTest {
        val movieEntity = DummyData.movieEntity
        val mappedData = movieCacheMapper.transformToDomain(listOf(movieEntity))
        assertThat(DummyData.movieEntity2.id).isNotEqualTo(mappedData.first().id)
        assertThat(DummyData.movieEntity2.title).isNotEqualTo(mappedData.first().title)
        assertThat(DummyData.movieEntity2.overview).isNotEqualTo(mappedData.first().overview)
    }
}
