package com.engie.eea_tech_interview.business.utils.mapper.base

interface BaseEntityMapper<T, D> {
    fun transformToDomain(type: T): D
}
