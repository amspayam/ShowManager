package com.combyne.core.usecase

interface SyncUseCase<RQ, RS> {
    fun executeSync(rq: RQ): RS
}