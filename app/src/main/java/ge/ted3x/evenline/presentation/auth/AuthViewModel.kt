/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 9:36 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 9:24 PM
 */

package ge.ted3x.evenline.presentation.auth

import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.screens.Screens
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val router: Router) : BaseViewModel() {

    fun navigateToSignUp() {
        router.navigateTo(Screens.SignUp())
    }
}
