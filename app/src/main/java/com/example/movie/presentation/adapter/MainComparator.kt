package com.example.movie.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movie.business.domain.model.Movie


class MainComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}
