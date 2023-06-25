/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:30 PM
 */

package ge.ted3x.evenline.domain

interface UserRepository {

    suspend fun signIn(email: String, password: String)

    suspend fun signUp(fullName: String, email: String, password: String)
}
