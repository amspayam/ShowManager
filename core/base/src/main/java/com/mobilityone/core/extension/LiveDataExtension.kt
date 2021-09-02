package com.combyne.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

inline fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, crossinline action: (t: T) -> Unit) {
    liveData?.observe(this, Observer { t -> action(t) })
}

fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> = Transformations.map(this, body)

fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> =
        Transformations.switchMap(this, body)