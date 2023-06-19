package com.example.movie.business.datasource.cache

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.movie.business.datasource.cache.dao.MovieDao
import com.example.movie.business.util.DummyData
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class GetMovieCacheSourceTest {

    private lateinit var movieDatabase: MovieDatabase
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var movieDao: MovieDao

    @Before
    fun initDb() {
        movieDatabase = Room.inMemoryDatabaseBuilder(
            context,
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()

        movieDao = movieDatabase.movieDao()
    }

    @Test
    fun `is searchResult is saved in db`() = runBlocking {
        movieDao.insert(listOf(DummyData.movieEntity))
        val savedMovies = movieDao.getMovies()
        Truth.assertThat(savedMovies).isNotNull()
    }

    @Test
    fun `is searchResult data correctly saved in db`() = runBlocking {
        movieDao.insert(listOf(DummyData.movieEntity))
        val savedMovies = movieDao.getMovies()
        Truth.assertThat(savedMovies).isNotNull()
        Truth.assertThat(savedMovies.first().first().id).isEqualTo(DummyData.movieEntity.id)
        Truth.assertThat(savedMovies.first().first().title).isEqualTo(DummyData.movieEntity.title)
        Truth.assertThat(savedMovies.first().first().overview)
            .isEqualTo(DummyData.movieEntity.overview)
    }

    @After
    fun tearDb() {
        movieDatabase.close()
    }
}
