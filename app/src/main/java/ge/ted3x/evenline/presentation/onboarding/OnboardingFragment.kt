/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 7:38 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 7:37 PM
 */

package ge.ted3x.evenline.presentation.onboarding

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.AppBarAction
import ge.ted3x.evenline.base.BaseFragment
import ge.ted3x.evenline.databinding.FragmentOnboardingBinding
import ge.ted3x.evenline.extensions.viewBinding

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<OnboardingViewModel>(R.layout.fragment_onboarding) {

    private val binding by viewBinding(FragmentOnboardingBinding::bind)

    override val viewModelClass = OnboardingViewModel::class

    override fun getAppBarActions(): List<AppBarAction> {
        return listOf(
            AppBarAction.Text(
                "SKIP",
                R.style.Label_Body_Medium_Medium,
                R.color.color_primary_base
            ) {
            }
        )
    }

    override fun onBindViewModel(vm: OnboardingViewModel) {
        vm.setIsOnboarded()
        binding.onboardingGetStartedBtn.setOnClickListener { vm.navigateToAuth() }
    }
}
