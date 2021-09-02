package com.combyne.uikit.extension.mutablelivedata

import androidx.lifecycle.MutableLiveData


/**
 * Created by Rasoul Miri on 5/16/20.
 */

fun <T> MutableLiveData<T>.postNotifyObserver() {
    this.postValue(this.value)
}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}
