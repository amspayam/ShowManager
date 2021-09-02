package com.combyne.showmanager.movies.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.combyne.showmanager.databinding.FragmentMoviesBinding
import com.combyne.showmanager.movies.presenter.adapter.MovieAdapter
import com.combyne.showmanager.movies.presenter.adapter.MovieDecoration
import com.combyne.uikit.base.BaseFragment
import com.combyne.uikit.base.viewmodel.MessageMaster
import com.combyne.uikit.base.viewmodel.MessageTypeEnum
import com.mobilityone.core.view.onViewData
import com.mobilityone.core.view.onViewError
import com.mobilityone.core.view.onViewLoading
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<MoviesViewModel>() {

    override val viewModel: MoviesViewModel by viewModel()
    private lateinit var binding: FragmentMoviesBinding
    private val movieAdapter by lazy {
        MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {
        initialRecyclerview()
    }

    private fun initialRecyclerview() {
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.moviesRecyclerView.addItemDecoration(MovieDecoration(requireContext()))
        binding.moviesRecyclerView.adapter = movieAdapter
    }

    override fun setupObserveData() {
        // <editor-fold desc="Observe History">
        viewModel.moviesViewStateLiveData.observe(viewLifecycleOwner) {
            it.onViewLoading { binding.swipeRefresh.isRefreshing = true }
                .onViewData {
                    binding.swipeRefresh.isRefreshing = false
                }
                .onViewError { messages, _ ->
                    showMessage(
                        MessageMaster(
                            type = MessageTypeEnum.SNACK_BAR,
                            message = messages
                        )
                    )
                    binding.swipeRefresh.isRefreshing = false
                }
        }
        // </editor-fold>

        // <editor-fold desc="Observe Adapter">
        viewModel.movieItemsLiveData.observe(viewLifecycleOwner) {
            movieAdapter.setItems(it)
        }
        // </editor-fold>
    }

    override fun setupListener() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMovies(first = 20, skip = 0)
        }
    }

    override fun onClick(v: View?) {
        super.onClick(v)
    }

}