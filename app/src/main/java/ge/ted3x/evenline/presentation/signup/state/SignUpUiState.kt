/*
 * Created by Tedo Manvelidze(ted3x) on 6/24/23, 2:13 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/24/23, 1:32 PM
 */

package ge.ted3x.evenline.presentation.signup.state

data class SignUpUiState(
    val errorMessage: String? = null,
    val isFullNameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true
)
