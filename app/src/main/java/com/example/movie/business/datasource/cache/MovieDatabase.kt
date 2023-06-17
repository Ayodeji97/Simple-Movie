package com.example.movie.business.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie.business.datasource.cache.dao.MovieDao
import com.example.movie.business.datasource.cache.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
