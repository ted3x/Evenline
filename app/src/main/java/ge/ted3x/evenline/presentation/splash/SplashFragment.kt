/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 5:39 PM
 */

package ge.ted3x.evenline.presentation.splash

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {

    override val viewModelClass = SplashViewModel::class

    override fun onBindViewModel(vm: SplashViewModel) {
        vm.navigateToNextScreen()
    }
}
