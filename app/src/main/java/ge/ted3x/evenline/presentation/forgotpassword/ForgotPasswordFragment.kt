/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 8:15 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 8:14 PM
 */

package ge.ted3x.evenline.presentation.forgotpassword

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment
import ge.ted3x.evenline.databinding.FragmentForgotPasswordBinding
import ge.ted3x.evenline.extensions.viewBinding

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<ForgotPasswordViewModel>(
    R.layout.fragment_forgot_password
) {

    private val binding by viewBinding(FragmentForgotPasswordBinding::bind)

    override val viewModelClass = ForgotPasswordViewModel::class

    override fun onBindViewModel(vm: ForgotPasswordViewModel) {
        binding.forgotPasswordBtn.setOnClickListener {
            vm.onForgotPassword(binding.forgotPasswordEmail.text)
        }
    }
}
