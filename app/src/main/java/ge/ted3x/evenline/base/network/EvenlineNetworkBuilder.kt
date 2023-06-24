/*
 * Created by Tedo Manvelidze(ted3x) on 6/24/23, 2:13 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/24/23, 1:32 PM
 */

package ge.ted3x.evenline.base.network

class EvenlineNetworkBuilder<Result> {

    private var onLoading: (() -> Unit)? = null
    private lateinit var onExecute: suspend () -> Result
    private lateinit var onSuccess: (Result) -> Unit
    private var onError: (suspend (EvenlineException) -> Unit)? = null

    fun onLoading(callback: () -> Unit) = apply {
        onLoading = callback
    }
    fun onExecute(callback: suspend () -> Result) = apply {
        onExecute = callback
    }
    fun onSuccess(callback: (Result) -> Unit) = apply {
        onSuccess = callback
    }
    fun onError(callback: suspend (EvenlineException) -> Unit) = apply {
        onError = callback
    }

    suspend fun execute() {
        onLoading?.invoke()
        try {
            val result = onExecute.invoke()
            onSuccess.invoke(result)
        } catch (e: EvenlineException) {
            onError?.invoke(e)
        }
    }
}
