/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 11:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 11:39 PM
 */

package ge.ted3x.evenline.domain

interface UserRepository {

    suspend fun signIn(email: String, password: String)

    suspend fun signUp(fullName: String, email: String, password: String)

    suspend fun resetPassword(email: String)
    suspend fun changePassword(code: String, password: String, confirmPassword: String)

    suspend fun verifyEmail(otp: String)
}
