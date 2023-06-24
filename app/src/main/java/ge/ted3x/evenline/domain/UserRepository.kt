/*
 * Created by Tedo Manvelidze(ted3x) on 6/24/23, 2:13 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/24/23, 1:39 PM
 */

package ge.ted3x.evenline.domain

interface UserRepository {

    suspend fun signUp(fullName: String, email: String, password: String)
}
