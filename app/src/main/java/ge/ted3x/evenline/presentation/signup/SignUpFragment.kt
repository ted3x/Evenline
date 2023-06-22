/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 9:36 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 9:24 PM
 */

package ge.ted3x.evenline.presentation.signup

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment

@AndroidEntryPoint
class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_sign_up) {

    override val viewModelClass = SignUpViewModel::class

    override fun onBindViewModel(vm: SignUpViewModel) {
    }
}
