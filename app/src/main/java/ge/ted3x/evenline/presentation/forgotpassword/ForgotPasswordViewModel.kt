/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 8:15 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 8:14 PM
 */

package ge.ted3x.evenline.presentation.forgotpassword

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.domain.ResetPasswordInteractor
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val resetPasswordInteractor: ResetPasswordInteractor
) : BaseViewModel(dispatcher) {

    fun onForgotPassword(email: String) {
        when {
            email.isEmpty() -> "Email is empty"
            else -> resetPassword(email)
        }
    }

    private fun resetPassword(email: String) {
        executeInteractor {
            onExecute { resetPasswordInteractor.execute(ResetPasswordInteractor.Params(email)) }
            onSuccess { }
            onError { showSnackbar(it.message) }
        }
    }
}
