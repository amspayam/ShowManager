package com.combyne.showmanager.movies.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.combyne.core.view.onViewData
import com.combyne.core.view.onViewError
import com.combyne.core.view.onViewLoading
import com.combyne.showmanager.databinding.FragmentMoviesBinding
import com.combyne.showmanager.movies.presenter.adapter.MovieAdapter
import com.combyne.showmanager.movies.presenter.adapter.MovieDecoration
import com.combyne.uikit.base.BaseFragment
import com.combyne.uikit.base.viewmodel.MessageMaster
import com.combyne.uikit.base.viewmodel.MessageTypeEnum
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
                .onViewData { data ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        data.collectLatest { list ->
                            binding.swipeRefresh.isRefreshing = false
                            movieAdapter.submitData(list)
                        }
                    }
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

    }

    override fun setupListener() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMovies()
        }
    }

}