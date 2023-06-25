/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:30 PM
 */

package ge.ted3x.evenline.presentation.signup

import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment
import ge.ted3x.evenline.databinding.FragmentSignUpBinding
import ge.ted3x.evenline.extensions.viewBinding

@AndroidEntryPoint
class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    override val viewModelClass = SignUpViewModel::class

    override fun onBindViewModel(vm: SignUpViewModel) {
        binding.authWithEmailBtn.setOnClickListener {
            val fullName = binding.signUpYourName.text.toString()
            val email = binding.signUpEmail.text.toString()
            val password = binding.signUpPassword.text.toString()
            vm.onSignUp(fullName, email, password)
        }

        vm.uiState.collectFlow {
            it.errorMessage?.let { msg -> Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show() }
            binding.signUpYourName.setErrorState(!it.isFullNameValid)
            binding.signUpEmail.setErrorState(!it.isEmailValid)
            binding.signUpPassword.setErrorState(!it.isPasswordValid)
        }
    }
}
