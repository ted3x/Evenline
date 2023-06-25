/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 6:00 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:57 PM
 */

package ge.ted3x.evenline.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ge.ted3x.evenline.base.network.EvenlineNetworkBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    val snackBarMessage get() = _snackBarMessage.asSharedFlow()
    private val _snackBarMessage = MutableSharedFlow<String>()
    protected fun BaseViewModel.execute(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(dispatcher) { block.invoke(this) }
    }

    protected fun <Result> BaseViewModel.executeInteractor(
        networkBuilder: EvenlineNetworkBuilder<Result>.() -> Unit
    ) {
        execute {
            val builder = EvenlineNetworkBuilder<Result>().apply { networkBuilder() }
            builder.execute()
        }
    }

    protected suspend fun showSnackbar(message: String) {
        _snackBarMessage.emit(message)
    }
}
