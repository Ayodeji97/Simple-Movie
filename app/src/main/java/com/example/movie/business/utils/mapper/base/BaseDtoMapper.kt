package com.example.movie.business.utils.mapper.base

interface BaseDtoMapper<T, E> {
    fun transformToEntity(type: T): E
}
