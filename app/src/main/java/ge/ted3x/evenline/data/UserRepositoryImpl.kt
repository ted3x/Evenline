/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:30 PM
 */

package ge.ted3x.evenline.data

import com.google.firebase.auth.FirebaseAuth
import ge.ted3x.evenline.base.network.EvenlineException
import ge.ted3x.evenline.domain.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : UserRepository {

    override suspend fun signIn(email: String, password: String) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
            // TODO store some data
        } catch (e: Exception) {
            throw EvenlineException.SignInFailed
        }
    }

    override suspend fun signUp(fullName: String, email: String, password: String) {
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (result.user == null) throw EvenlineException.UserCreationFailed
        } catch (e: Exception) {
            throw EvenlineException.UserCreationFailed
        }
    }
}
