/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:54 PM
 */

package ge.ted3x.evenline.presentation.auth

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment
import ge.ted3x.evenline.databinding.FragmentAuthBinding
import ge.ted3x.evenline.extensions.spannableClick
import ge.ted3x.evenline.extensions.viewBinding

@AndroidEntryPoint
class AuthFragment : BaseFragment<AuthViewModel>(R.layout.fragment_auth) {

    private val binding by viewBinding(FragmentAuthBinding::bind)

    override val viewModelClass = AuthViewModel::class
    override fun onBindViewModel(vm: AuthViewModel) {
        binding.authWithEmailBtn.setOnClickListener { vm.navigateToSignIn() }
        binding.authSignUp.spannableClick(spannableClick = { vm.navigateToSignUp() })
    }
}
