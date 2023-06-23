/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.base.interactor

import ge.ted3x.evenline.base.dispatcher.EvenlineCoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseInteractor<Params, Result>(private val dispatchers: EvenlineCoroutineDispatchers) {

    protected abstract suspend fun operation(params: Params): Result

    suspend fun execute(params: Params, dispatcher: CoroutineDispatcher = dispatchers.io): Result {
        return withContext(dispatcher) {
            operation(params)
        }
    }
}
