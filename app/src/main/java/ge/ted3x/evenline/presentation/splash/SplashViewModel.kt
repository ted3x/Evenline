/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.presentation.splash

import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.data.SharedPreferencesHandler
import ge.ted3x.evenline.screens.Screens
import ge.ted3x.evenline.utils.EvenlineConstants
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class SplashViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    sharedPreferencesHandler: SharedPreferencesHandler,
    private val router: Router
) : BaseViewModel(dispatcher) {

    private val isOnboarded = sharedPreferencesHandler.readBoolean(
        EvenlineConstants.EVENLINE_IS_ONBOARDED
    )
    fun navigateToNextScreen() {
        if (isOnboarded) {
            router.newRootScreen(Screens.Onboarding())
        } else {
            router.newRootScreen(Screens.Onboarding())
        }
    }
}
