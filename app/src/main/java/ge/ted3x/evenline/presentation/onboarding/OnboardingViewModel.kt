/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.presentation.onboarding

import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.data.SharedPreferencesHandler
import ge.ted3x.evenline.screens.Screens
import ge.ted3x.evenline.utils.EvenlineConstants
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val sharedPreferencesHandler: SharedPreferencesHandler,
    private val router: Router
) : BaseViewModel(dispatcher) {

    fun setIsOnboarded() {
        sharedPreferencesHandler.writeBoolean(EvenlineConstants.EVENLINE_IS_ONBOARDED, true)
    }

    fun navigateToAuth() {
        router.newRootScreen(Screens.Auth())
    }
}
