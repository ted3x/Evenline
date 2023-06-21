/*
 * Created by Tedo Manvelidze(ted3x) on 6/21/23, 11:28 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/21/23, 11:28 PM
 */

package ge.ted3x.evenline.presentation.splash

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment
import ge.ted3x.evenline.databinding.FragmentSplashBinding
import ge.ted3x.evenline.extensions.viewBinding

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {

    private val binding by viewBinding(FragmentSplashBinding::bind)

    override val viewModelClass = SplashViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.print()
    }
}
