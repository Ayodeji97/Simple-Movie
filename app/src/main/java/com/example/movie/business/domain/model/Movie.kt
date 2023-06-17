package com.engie.eea_tech_interview.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val originalTitle: String,
    val mediaType: String,
    val originalLanguage: String,
    val title: String,
    val voteCount: Int,
) : Parcelable
