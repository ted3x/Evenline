/*
 * Created by Tedo Manvelidze(ted3x) on 6/24/23, 2:13 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/24/23, 2:11 PM
 */

package ge.ted3x.evenline.presentation.signup

import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.domain.SignUpInteractor
import ge.ted3x.evenline.presentation.signup.state.SignUpUiState
import ge.ted3x.evenline.screens.Screens
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SignUpViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val signUpInteractor: SignUpInteractor,
    private val router: Router
) : BaseViewModel(dispatcher) {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(SignUpUiState())

    fun onSignUp(fullName: String, email: String, password: String) {
        when {
            fullName.isEmpty() -> {
                _uiState.update {
                    it.copy(errorMessage = "Enter your name", isFullNameValid = false)
                }
            }
            email.isEmpty() -> {
                _uiState.update { it.copy(errorMessage = "Enter your email", isEmailValid = false) }
            }
            !email.isEmailValid() -> {
                _uiState.update {
                    it.copy(errorMessage = "Email is not valid", isEmailValid = false)
                }
            }
            password.isEmpty() -> {
                _uiState.update {
                    it.copy(errorMessage = "Enter your password", isPasswordValid = false)
                }
            }
            password.length !in (6..32) -> {
                _uiState.update {
                    it.copy(
                        errorMessage = "Password should be at least 6 symbols and at most 32",
                        isPasswordValid = false
                    )
                }
            }
            else -> signUp(fullName, email, password)
        }
    }

    private fun signUp(fullName: String, email: String, password: String) {
        executeInteractor {
            onLoading {
            }
            onExecute {
                signUpInteractor.execute(SignUpInteractor.Params(fullName, email, password))
            }
            onSuccess {
                router.newRootScreen(Screens.Dashboard())
            }
            onError { error ->
                _uiState.update { it.copy(errorMessage = error.message) }
            }
        }
    }

    private fun String.isEmailValid() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
