package com.engie.eea_tech_interview.presentation.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.engie.eea_tech_interview.R
import com.engie.eea_tech_interview.databinding.FragmentMainBinding
import com.engie.eea_tech_interview.presentation.adapter.MainAdapter
import com.engie.eea_tech_interview.presentation.utils.onQueryTextChanged
import com.engie.eea_tech_interview.presentation.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var currentBinding: FragmentMainBinding? = null
    private val ui get() = currentBinding!!

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var mainAdapter: MainAdapter
    private lateinit var menuHost: MenuHost

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)
        currentBinding = FragmentMainBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getSearchResultSubscriber()

        menuHost = requireActivity()
        initMenu()
    }

    private fun getSearchResultSubscriber() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.getSearchResultViewState.collectLatest { state ->
                ui.apply {
                    progressBar.isVisible = state.isLoading
                    if (state.error != "") {
                        showSnackBar(requireView(), state.error)
                    } else {
                        state.searchResult?.let { movies ->
                            if (movies.isEmpty()) {
                                fragmentErrorTv.visibility = View.VISIBLE
                                recyclerViewMovie.visibility = View.GONE
                                fragmentErrorTv.text = getString(R.string.search_not_found)
                            } else {
                                fragmentErrorTv.visibility = View.GONE
                                recyclerViewMovie.visibility = View.VISIBLE
                                mainAdapter.submitList(movies)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        mainAdapter = MainAdapter(
            onMovieClicked = { movie ->
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(movie)
                findNavController().navigate(action)
            }
        )

        ui.apply {
            recyclerViewMovie.apply {
                adapter = mainAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun initMenu() {
        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_fragment_movie, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.action_search -> {
                            // Action here
                            val searchView = menuItem.actionView as SearchView
                            searchView.onQueryTextChanged { searchQuery ->
                                mainViewModel.onTriggeredEvent(MainViewEvent.GetSearchResult(searchQuery))
                            }
                            scrollUp()
                            true
                        }
                        else -> false
                    }
                }
            },
            viewLifecycleOwner, Lifecycle.State.STARTED
        )
    }

    private fun scrollUp() {
        lifecycleScope.launch {
            delay(100)
            ui.recyclerViewMovie.scrollToPosition(0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}
