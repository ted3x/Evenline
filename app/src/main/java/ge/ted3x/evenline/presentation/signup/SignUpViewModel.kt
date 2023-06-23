/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.presentation.signup

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.domain.SignUpInteractor
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class SignUpViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val signUpInteractor: SignUpInteractor
) : BaseViewModel(dispatcher) {

    fun signUp() {
        executeInteractor {
            onLoading {
            }
            onExecute {
                signUpInteractor.execute(SignUpInteractor.Params("", "", ""))
            }
            onSuccess {
            }
            onError {
            }
        }
    }
}
