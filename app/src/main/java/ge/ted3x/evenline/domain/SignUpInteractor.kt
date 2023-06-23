/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.domain

import ge.ted3x.evenline.base.dispatcher.EvenlineCoroutineDispatchers
import ge.ted3x.evenline.base.interactor.BaseInteractor
import javax.inject.Inject

class SignUpInteractor @Inject constructor(
    dispatchers: EvenlineCoroutineDispatchers,
    private val repository: UserRepository
) : BaseInteractor<SignUpInteractor.Params, Boolean>(dispatchers) {

    data class Params(val email: String, val password: String, val fullName: String)

    override suspend fun operation(params: Params): Boolean {
        return repository.signUp(
            fullName = params.fullName,
            email = params.email,
            password = params.password
        )
    }
}
