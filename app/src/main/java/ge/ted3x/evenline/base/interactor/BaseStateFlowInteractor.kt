/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.base.interactor

import ge.ted3x.evenline.base.dispatcher.EvenlineCoroutineDispatchers
import ge.ted3x.evenline.base.network.ResultWrapper
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

abstract class BaseStateFlowInteractor<Params, Result> {
    @Inject
    lateinit var dispatcher: EvenlineCoroutineDispatchers
    protected abstract suspend fun operation(params: Params): StateFlow<ResultWrapper<Result>>

    suspend fun execute(params: Params, dispatchers: CoroutineDispatcher = dispatcher.io): StateFlow<ResultWrapper<Result>> {
        return withContext(dispatchers) {
            operation(params)
        }
    }
}
