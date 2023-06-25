/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 8:15 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 8:14 PM
 */

package ge.ted3x.evenline.presentation.changepassword

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.domain.ChangePasswordInteractor
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val changePasswordInteractor: ChangePasswordInteractor
) : BaseViewModel(dispatcher) {

    fun onResetPassword(code: String, password: String, confirmPassword: String) {
        when {
            password.isEmpty() -> "Password is empty"
            confirmPassword.isEmpty() -> "Confirmation password is empty"
            password != confirmPassword -> "Passwords don't match"
            else -> resetPassword(code, password, confirmPassword)
        }
    }

    private fun resetPassword(code: String, password: String, confirmPassword: String) {
        executeInteractor {
            onExecute {
                changePasswordInteractor.execute(
                    ChangePasswordInteractor.Params(
                        code,
                        password,
                        confirmPassword
                    )
                )
            }
            onSuccess { }
            onError { showSnackbar(it.message) }
        }
    }
}
