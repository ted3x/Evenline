/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:34 PM
 */

package ge.ted3x.evenline.presentation.auth

import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.screens.Screens
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class AuthViewModel @Inject constructor(dispatcher: CoroutineDispatcher, private val router: Router) :
    BaseViewModel(dispatcher) {

    fun navigateToSignUp() {
        router.navigateTo(Screens.SignUp())
    }

    fun navigateToSignIn() {
        router.navigateTo(Screens.SignIn())
    }
}
