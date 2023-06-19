package com.example.movie.business.utils.mapper.cachemapper


import com.example.movie.business.datasource.cache.model.MovieEntity
import com.example.movie.business.domain.model.Movie
import com.example.movie.business.utils.mapper.base.BaseEntityMapper
import javax.inject.Inject

class MovieCacheMapper @Inject constructor() : BaseEntityMapper<List<MovieEntity>, List<Movie>> {
    override fun transformToDomain(type: List<MovieEntity>): List<Movie> =
        type.map { movieEntity ->
            Movie(
                posterPath = IMAGE_PREFIX + movieEntity.posterPath,
                overview = movieEntity.overview,
                releaseDate = movieEntity.releaseDate,
                originalLanguage = movieEntity.originalLanguage,
                mediaType = movieEntity.mediaType,
                originalTitle = movieEntity.originalTitle,
                title = movieEntity.title,
                voteCount = movieEntity.voteCount,
                id = movieEntity.id
            )
        }

    companion object {
        const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/original"
    }
}
