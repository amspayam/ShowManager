package com.combyne.core.usecase

interface AsyncSuspendUseCase<RQ, RS> {
   suspend fun executeAsync(rq: RQ): RS
}