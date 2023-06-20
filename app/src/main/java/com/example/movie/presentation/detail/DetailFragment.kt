package com.example.movie.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.business.domain.model.Movie
import com.example.movie.business.utils.mapper.cachemapper.MovieCacheMapper.Companion.IMAGE_PREFIX
import com.example.movie.databinding.FragmentDetailBinding
import com.example.movie.presentation.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var currentBinding: FragmentDetailBinding? = null
    private val ui get() = currentBinding!!

    private val detailViewModel: DetailViewModel by viewModels()

    private var movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentBinding = FragmentDetailBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = DetailFragmentArgs.fromBundle(requireArguments()).movieId

        detailViewModel.onTriggeredEvent(DetailViewEvent.GetMovieDetail(movieId))

        getDetailSubscriber()

        ui.backBtn.setOnClickListener { findNavController().navigate(R.id.mainFragment) }
    }

    private fun getDetailSubscriber() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailViewModel.getMovieDetailViewState.collectLatest { state ->
                showMovieDetail(state)
            }
        }
    }


    private fun showMovieDetail(state: DetailViewState) {
        with(ui) {
            progressBar.isVisible = state.isLoading
            state.movie?.let {
                updateView(it)
            }
        }
    }

    private fun updateView(movie: Movie) {
        with(ui) {
            titleTv.text = movie.title
            overviewTv.text = movie.overview
            productAvatarIv.loadImage(IMAGE_PREFIX + movie.posterPath)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}
