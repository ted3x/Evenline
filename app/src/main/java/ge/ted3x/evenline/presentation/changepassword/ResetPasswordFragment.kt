/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 8:15 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 8:14 PM
 */

package ge.ted3x.evenline.presentation.changepassword

import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment
import ge.ted3x.evenline.databinding.FragmentChangePasswordBinding
import ge.ted3x.evenline.extensions.viewBinding

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragment<ResetPasswordViewModel>(
    R.layout.fragment_change_password
) {

    private val code by lazy {
        arguments?.getString(OOBCODE) ?: throw RuntimeException("OOB CODE Can't be null")
    }

    private val binding by viewBinding(FragmentChangePasswordBinding::bind)

    override val viewModelClass = ResetPasswordViewModel::class

    override fun onBindViewModel(vm: ResetPasswordViewModel) {
        binding.resetPasswordBtn.setOnClickListener {
            vm.onResetPassword(
                code = code,
                password = binding.resetPasswordField.text,
                confirmPassword = binding.resetPasswordConfirmField.text
            )
        }
    }

    companion object {
        private const val OOBCODE = "oobcode"
        fun newInstance(code: String) = ResetPasswordFragment().apply {
            arguments = bundleOf(OOBCODE to code)
        }
    }
}
