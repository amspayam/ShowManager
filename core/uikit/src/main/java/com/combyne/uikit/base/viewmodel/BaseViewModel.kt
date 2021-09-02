package com.combyne.uikit.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.combyne.uikit.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val message: SingleLiveEvent<MessageMaster> = SingleLiveEvent()

    fun track(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(context = Dispatchers.Main, block = block)
    }

}