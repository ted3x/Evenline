/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:30 PM
 */

package ge.ted3x.evenline.domain

import ge.ted3x.evenline.base.dispatcher.EvenlineCoroutineDispatchers
import ge.ted3x.evenline.base.interactor.BaseInteractor
import javax.inject.Inject

class SignInInteractor @Inject constructor(
    dispatchers: EvenlineCoroutineDispatchers,
    private val userRepository: UserRepository
) :
    BaseInteractor<SignInInteractor.Params, Unit>(dispatchers) {

    override suspend fun operation(params: Params) {
        TODO("Not yet implemented")
    }

    data class Params(val email: String, val password: String)
}
