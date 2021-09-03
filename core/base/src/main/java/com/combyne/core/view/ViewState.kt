package com.combyne.core.view

sealed class ViewState<out T> {
    class ViewError(var message: String = "", var status: Int = 0) : ViewState<Nothing>()
    class ViewEmpty(var message: Int = 0, var icon: Int = 0) : ViewState<Nothing>()
    class ViewData<T>(val data: T) : ViewState<T>()
    object ViewLoading : ViewState<Nothing>()
}

inline fun <T> ViewState<T>.onViewError(body: (messages: String, status: Int) -> Unit): ViewState<T> {
    if (this is ViewState.ViewError) body(message, status)
    return this
}

inline fun <T> ViewState<T>.onViewEmpty(body: (messages: Int, icon: Int) -> Unit): ViewState<T> {
    if (this is ViewState.ViewEmpty) body(message, icon)
    return this
}

inline fun <T> ViewState<T>.onViewData(body: (data: T) -> Unit): ViewState<T> {
    if (this is ViewState.ViewData) body(data)
    return this
}

inline fun <T> ViewState<T>.onViewLoading(body: () -> Unit): ViewState<T> {
    if (this is ViewState.ViewLoading) body()
    return this
}