package com.example.movie.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.movie.business.domain.model.Movie
import com.example.movie.databinding.MovieListItemBinding
import com.example.movie.presentation.utils.loadImage


class MainViewHolder(
    private val ui: MovieListItemBinding,
    private val onMovieClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(ui.root) {

    fun bind(movie: Movie) {
        with(ui) {
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
