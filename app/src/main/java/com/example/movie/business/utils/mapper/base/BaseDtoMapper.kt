package com.engie.eea_tech_interview.business.utils.mapper.base

interface BaseDtoMapper<T, E> {
    fun transformToEntity(type: T): E
}
