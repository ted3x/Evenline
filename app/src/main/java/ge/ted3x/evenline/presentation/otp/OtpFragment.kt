/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 11:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 11:39 PM
 */

package ge.ted3x.evenline.presentation.otp

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment
import ge.ted3x.evenline.components.PinInputCompleteListener
import ge.ted3x.evenline.databinding.FragmentOtpBinding
import ge.ted3x.evenline.extensions.viewBinding

@AndroidEntryPoint
class OtpFragment : BaseFragment<OtpViewModel>(R.layout.fragment_otp) {

    private val binding by viewBinding(FragmentOtpBinding::bind)

    override val viewModelClass = OtpViewModel::class

    override fun onBindViewModel(vm: OtpViewModel) {
        binding.otpResend.setOnClickListener { vm.resendCode() }
        binding.otpField.listener = object : PinInputCompleteListener {
            override fun onComplete(pin: String) {
                vm.onOtpEnter(pin)
            }
        }
        binding.otpBtn.setOnClickListener { vm.onVerify(binding.otpField.text) }
    }

    companion object {
        fun newInstance() = OtpFragment()
    }
}
