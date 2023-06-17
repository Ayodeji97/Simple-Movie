package com.engie.eea_tech_interview.business.utils.mapper.remotemapper

import com.engie.eea_tech_interview.business.datasource.cache.model.MovieEntity
import com.engie.eea_tech_interview.business.datasource.remote.model.MovieDto
import com.engie.eea_tech_interview.business.utils.mapper.base.BaseDtoMapper
import javax.inject.Inject

class MovieDtoMapper @Inject constructor() : BaseDtoMapper<List<MovieDto>, List<MovieEntity>> {
    override fun transformToEntity(type: List<MovieDto>): List<MovieEntity> =
        type.map { movieDto ->
            MovieEntity(
                id = movieDto.id,
                posterPath = movieDto.posterPath ?: "",
                overview = movieDto.overview ?: "",
                releaseDate = movieDto.releaseDate ?: "",
                originalTitle = movieDto.originalTitle ?: "",
                mediaType = movieDto.mediaType ?: "",
                originalLanguage = movieDto.originalLanguage ?: "",
                title = movieDto.title ?: "",
                voteCount = movieDto.voteCount ?: 0
            )
        }
}
