/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.base.network

sealed class EvenlineException(override val message: String) : Exception(message) {

    object UserCreationFailed : EvenlineException("Failed to create user")
}