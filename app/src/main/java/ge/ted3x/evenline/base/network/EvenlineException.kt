/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:30 PM
 */

package ge.ted3x.evenline.base.network

sealed class EvenlineException(override val message: String) : Exception(message) {

    object SignInFailed : EvenlineException("Sign in failed")
    object UserCreationFailed : EvenlineException("Failed to create user")
}
