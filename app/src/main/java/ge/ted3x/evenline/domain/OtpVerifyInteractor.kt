/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 11:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 11:39 PM
 */

package ge.ted3x.evenline.domain

import ge.ted3x.evenline.base.dispatcher.EvenlineCoroutineDispatchers
import ge.ted3x.evenline.base.interactor.BaseInteractor
import javax.inject.Inject

class OtpVerifyInteractor @Inject constructor(
    dispatchers: EvenlineCoroutineDispatchers,
    private val userRepository: UserRepository
) :
    BaseInteractor<OtpVerifyInteractor.Params, Unit>(dispatchers) {

    override suspend fun operation(params: Params) {
        userRepository.verifyEmail(params.otp)
    }

    data class Params(val otp: String)
}
