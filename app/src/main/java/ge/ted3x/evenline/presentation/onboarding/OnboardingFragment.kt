/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:39 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 5:39 PM
 */

package ge.ted3x.evenline.presentation.onboarding

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.AppBarAction
import ge.ted3x.evenline.base.BaseFragment

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<OnboardingViewModel>(R.layout.fragment_onboarding) {

    override val viewModelClass = OnboardingViewModel::class

    override fun getAppBarActions(): List<AppBarAction>? {
        return listOf(
            AppBarAction.Text(
                "SKIP",
                R.style.Label_Body_Medium_ExtraBold,
                R.color.color_primary_base
            ) {
            }
        )
    }

    override fun onBindViewModel(vm: OnboardingViewModel) {
        vm.setIsOnboarded()
    }
}
