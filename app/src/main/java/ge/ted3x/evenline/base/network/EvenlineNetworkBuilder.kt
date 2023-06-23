/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.base.network

class EvenlineNetworkBuilder<Result> {

    private var onLoading: (() -> Unit)? = null
    private lateinit var onExecute: suspend () -> Result
    private lateinit var onSuccess: (Result) -> Unit
    private var onError: ((EvenlineException) -> Unit)? = null

    fun <Result> EvenlineNetworkBuilder<Result>.onLoading(callback: () -> Unit) = apply {
        onLoading = callback
    }
    fun <Result> EvenlineNetworkBuilder<Result>.onExecute(callback: suspend () -> Result) = apply {
        onExecute = callback
    }
    fun <Result> EvenlineNetworkBuilder<Result>.onSuccess(callback: (Result) -> Unit) = apply {
        onSuccess = callback
    }
    fun <Result> EvenlineNetworkBuilder<Result>.onError(callback: (EvenlineException) -> Unit) = apply {
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
