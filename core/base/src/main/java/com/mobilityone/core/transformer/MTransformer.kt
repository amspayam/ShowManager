package com.combyne.core.transformer

import io.reactivex.MaybeTransformer

abstract class MTransformer<T> : MaybeTransformer<T, T>