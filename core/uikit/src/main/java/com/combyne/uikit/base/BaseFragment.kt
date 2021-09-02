package com.combyne.uikit.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.combyne.uikit.base.viewmodel.BaseViewModel
import com.combyne.uikit.base.viewmodel.MessageMaster
import com.combyne.uikit.base.viewmodel.MessageTypeEnum
import com.combyne.uikit.components.snackbar.SnackBarComponent
import com.combyne.uikit.components.snackbar.StateEnums

abstract class BaseFragment<VM : BaseViewModel> : Fragment(),
    View.OnClickListener {

    open val viewModel: VM? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.message?.observe(viewLifecycleOwner) {
            showMessage(it)
        }
        setupViews()
        setupListener()
        setupToolbar()
        setupBackStackEntry()
        setupObserveData()
    }

    abstract fun setupViews()

    open fun setupToolbar() {}

    open fun setupBackStackEntry() {}

    open fun setupObserveData() {}

    open fun setupListener() {}

    override fun onClick(v: View?) {
    }

    protected fun onClickListeners(vararg views: View) {
        for (view in views)
            view.setOnClickListener(this)
    }

    open fun showMessage(message: MessageMaster) {
        message.text = message.message ?: message.messageResourceId?.let {
            getString(it)
        } ?: "Error"
        when (message.type) {
            MessageTypeEnum.TOAST -> {
                Toast.makeText(
                    requireContext(),
                    message.text,
                    Toast.LENGTH_LONG
                ).show()
            }
            MessageTypeEnum.SNACK_BAR -> {
                SnackBarComponent(view, message.text, StateEnums.ERROR)
            }
        }
    }

}