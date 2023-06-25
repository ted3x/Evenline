/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 8:15 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 8:14 PM
 */

package ge.ted3x.evenline.presentation.signin

import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.domain.SignInInteractor
import ge.ted3x.evenline.screens.Screens
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class SignInViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val signInInteractor: SignInInteractor,
    private val router: Router
) : BaseViewModel(dispatcher) {

    fun onSignIn(email: String, password: String) {
        when {
            email.isEmpty() -> "Email is empty"
            password.isEmpty() -> "Password is empty"
            else -> signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        executeInteractor {
            onExecute { signInInteractor.execute(SignInInteractor.Params(email, password)) }
            onSuccess { router.newRootScreen(Screens.Dashboard()) }
            onError { showSnackbar(it.message) }
        }
    }

    fun navigateToForgotPassword() {
        router.navigateTo(Screens.ForgotPassword())
    }
}
