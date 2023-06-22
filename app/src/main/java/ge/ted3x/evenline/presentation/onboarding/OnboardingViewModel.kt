/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:39 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 5:39 PM
 */

package ge.ted3x.evenline.presentation.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.data.SharedPreferencesHandler
import ge.ted3x.evenline.utils.EvenlineConstants
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val sharedPreferencesHandler: SharedPreferencesHandler
) :
    BaseViewModel() {

    fun setIsOnboarded() {
        sharedPreferencesHandler.writeBoolean(EvenlineConstants.EVENLINE_IS_ONBOARDED, true)
    }
}