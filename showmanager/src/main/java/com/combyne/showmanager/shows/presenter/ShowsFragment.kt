package com.combyne.showmanager.shows.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.combyne.showmanager.R
import com.combyne.showmanager.databinding.FragmentShowsBinding
import com.combyne.uikit.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowsFragment : BaseFragment<ShowsViewModel>() {

    override val viewModel: ShowsViewModel by viewModel()
    private lateinit var binding: FragmentShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {

    }

    override fun setupObserveData() {

    }

    override fun setupListener() {
        onClickListeners(binding.addNewTvShowButton, binding.showTvListButton)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.addNewTvShowButton -> navigateToAddShowFragment()
            R.id.showTvListButton -> navigateMoviesFragment()
        }
    }

    private fun navigateToAddShowFragment() {
        findNavController().navigate(R.id.action_showsFragment_to_addShowFragment)
    }

    private fun navigateMoviesFragment() {
        findNavController().navigate(R.id.action_showsFragment_to_moviesFragment)
    }

}