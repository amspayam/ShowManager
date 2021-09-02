package com.combyne.core.usecase

interface AsyncUseCase<RQ, RS> {
    fun executeAsync(rq: RQ): RS
}