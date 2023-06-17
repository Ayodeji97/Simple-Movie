package com.example.movie.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.business.domain.model.Movie
import com.example.movie.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var currentBinding: FragmentDetailBinding? = null
    private val ui get() = currentBinding!!

    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)
        currentBinding = FragmentDetailBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = DetailFragmentArgs.fromBundle(requireArguments()).movie
        movie?.let {
            updateView(it)
        }

        ui.backBtn.setOnClickListener { findNavController().navigate(R.id.mainFragment) }
    }

    private fun updateView(movie: Movie) {
        ui.apply {
            titleTv.text = movie.title
            overviewTv.text = movie.overview
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}
