package com.combyne.showmanager.addmovie.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.combyne.core.view.onViewData
import com.combyne.core.view.onViewError
import com.combyne.core.view.onViewLoading
import com.combyne.showmanager.R
import com.combyne.showmanager.databinding.FragmentAddMovieBinding
import com.combyne.uikit.base.BaseFragment
import com.combyne.uikit.base.viewmodel.MessageMaster
import com.combyne.uikit.base.viewmodel.MessageTypeEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMovieFragment : BaseFragment<AddMovieViewModel>() {

    override val viewModel: AddMovieViewModel by viewModel()
    private lateinit var binding: FragmentAddMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {

    }

    override fun setupObserveData() {
        viewModel.addShowViewStateLiveData.observe(viewLifecycleOwner) { response ->
            response.onViewLoading { binding.saveButton.startLoading() }
                .onViewData {
                    binding.saveButton.stopLoading()
                    showMessage(
                        MessageMaster(
                            type = MessageTypeEnum.TOAST,
                            message = "Movie ${it?.title()} added successfully"
                        )
                    ).also {
                        findNavController().popBackStack()
                    }
                }
                .onViewError { messages, _ ->
                    binding.saveButton.stopLoading()
                    showMessage(
                        MessageMaster(
                            type = MessageTypeEnum.SNACK_BAR,
                            message = messages
                        )
                    )
                }
        }
    }

    override fun setupListener() {
        onClickListeners(binding.saveButton)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        viewModel.addNewShow(
            title = binding.tvShowEditText.text,
            releaseDate = binding.releaseDateEditText.text,
            seasons = binding.seasonsEditText.text
        )
    }

    override fun showMessage(message: MessageMaster) {
        super.showMessage(message)
        if (message.type == MessageTypeEnum.VIEW) {
            when (message.viewId) {
                R.id.tvShowEditText -> binding.tvShowEditText.setError(message.text)
                R.id.releaseDateEditText -> binding.releaseDateEditText.setError(message.text)
                R.id.seasonsEditText -> binding.seasonsEditText.setError(message.text)
            }
        }
    }

}