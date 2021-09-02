package com.combyne.core.transformer

import io.reactivex.ObservableTransformer

abstract class OTransformer<T> : ObservableTransformer<T, T>