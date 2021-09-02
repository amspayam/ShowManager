package com.combyne.showmanager.movies.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.combyne.showmanager.databinding.FragmentMoviesBinding
import com.combyne.uikit.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<MoviesViewModel>() {

    override val viewModel: MoviesViewModel by viewModel()
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.moviesRecyclerView.adapter = adapter

    }

    override fun setupObserveData() {
        viewModel.addShowViewStateLiveData.observe(viewLifecycleOwner) { response ->
        }
    }

    override fun setupListener() {
    }

    override fun onClick(v: View?) {
        super.onClick(v)
    }

}