/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 8:15 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 8:14 PM
 */

package ge.ted3x.evenline.presentation.signin

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment
import ge.ted3x.evenline.databinding.FragmentSignInBinding
import ge.ted3x.evenline.extensions.viewBinding

@AndroidEntryPoint
class SignInFragment : BaseFragment<SignInViewModel>(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)

    override val viewModelClass = SignInViewModel::class

    override fun onBindViewModel(vm: SignInViewModel) {
        binding.signInBtn.setOnClickListener {
            val email = binding.signInEmail.text.toString()
            val password = binding.signInPassword.text.toString()
            vm.onSignIn(email, password)
        }
        binding.forgotPassword.setOnClickListener { vm.navigateToForgotPassword() }
    }
}
