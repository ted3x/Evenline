/*
 * Created by Tedo Manvelidze(ted3x) on 6/24/23, 2:13 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/24/23, 2:11 PM
 */

package ge.ted3x.evenline.data

import com.google.firebase.auth.FirebaseAuth
import ge.ted3x.evenline.base.network.EvenlineException
import ge.ted3x.evenline.domain.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : UserRepository {
    override suspend fun signUp(fullName: String, email: String, password: String) {
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (result.user == null) throw EvenlineException.UserCreationFailed
        } catch (e: Exception) {
            throw EvenlineException.UserCreationFailed
        }
    }
}
