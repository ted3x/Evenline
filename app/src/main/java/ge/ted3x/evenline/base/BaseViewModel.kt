/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ge.ted3x.evenline.base.network.EvenlineNetworkBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

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
}
