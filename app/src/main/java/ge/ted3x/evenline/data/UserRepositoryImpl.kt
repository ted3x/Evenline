/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 11:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 11:39 PM
 */

package ge.ted3x.evenline.data

import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import ge.ted3x.evenline.base.network.EvenlineException
import ge.ted3x.evenline.domain.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : UserRepository {

    override suspend fun signIn(email: String, password: String) {
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result.user == null) throw EvenlineException.SignInFailed
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

    override suspend fun resetPassword(email: String) {
        try {
            val actionCodeSettings = ActionCodeSettings.newBuilder().apply {
                url = "https://evenline.app/forgot-password"
                handleCodeInApp = true
            }.build()
            firebaseAuth.sendPasswordResetEmail(email, actionCodeSettings).await()
        } catch (e: Exception) {
            throw EvenlineException.PasswordResetFailed(e.message ?: "Password reset failed")
        }
    }

    override suspend fun changePassword(code: String, password: String, confirmPassword: String) {
        try {
            firebaseAuth.confirmPasswordReset(code, password)
        } catch (e: Exception) {
            throw EvenlineException.PasswordChangeFailed(e.message ?: "Password change failed")
        }
    }

    override suspend fun verifyEmail(otp: String) {
        try {
            firebaseAuth.currentUser?.sendEmailVerification(
                actionCodeSettings {
                    handleCodeInApp = true
                    url = "https://evenline.app/verify-mail"
                }
            )
        } catch (e: Exception) {
            throw EvenlineException.EmailVerifyOtpFailed(e.message ?: "")
        }
    }
}
