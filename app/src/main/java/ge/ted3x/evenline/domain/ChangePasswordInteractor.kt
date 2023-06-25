/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 8:15 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 8:14 PM
 */

package ge.ted3x.evenline.domain

import ge.ted3x.evenline.base.dispatcher.EvenlineCoroutineDispatchers
import ge.ted3x.evenline.base.interactor.BaseInteractor
import javax.inject.Inject

class ChangePasswordInteractor @Inject constructor(
    dispatchers: EvenlineCoroutineDispatchers,
    private val userRepository: UserRepository
) :
    BaseInteractor<ChangePasswordInteractor.Params, Unit>(dispatchers) {

    override suspend fun operation(params: Params) {
        userRepository.changePassword(
            code = params.code,
            password = params.password,
            confirmPassword = params.confirmPassword
        )
    }

    data class Params(val code: String, val password: String, val confirmPassword: String)
}
