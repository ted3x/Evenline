/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 11:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 11:39 PM
 */

package ge.ted3x.evenline.presentation.otp

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import ge.ted3x.evenline.domain.OtpVerifyInteractor
import ge.ted3x.evenline.utils.EvenlineConstants.EVENLINE_OTP_MAX_LENGTH
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

// Could be made with coordinator, so it can handle different otpVerificators, otp verificator might be interface
@HiltViewModel
class OtpViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val otpVerifyInteractor: OtpVerifyInteractor
) : BaseViewModel(dispatcher) {
    fun resendCode() {
        TODO("Not yet implemented")
    }

    fun onOtpEnter(otp: String) {
        executeInteractor {
            onExecute { otpVerifyInteractor.execute(OtpVerifyInteractor.Params(otp)) }
            onSuccess { }
            onError { showSnackbar(it.message) }
        }
    }

    fun onVerify(text: String) {
        when {
            text.length != EVENLINE_OTP_MAX_LENGTH -> "Please fill otp"
            else -> onOtpEnter(text)
        }
    }
}
