package com.example.movie.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.movie.business.domain.model.Movie


class MainViewHolder(
    private val ui: MovieListItemBinding,
    private val onMovieClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(ui.root) {

    fun bind(movie: Movie) {
        ui.apply {
            movieTitleTv.text = movie.title
            movieAvatarIv.loadImage(movie.posterPath)
        }
    }

    init {
        ui.apply {
            root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onMovieClicked(position)
                }
            }
        }
    }
}
