/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 11:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 11:39 PM
 */

package ge.ted3x.evenline.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ge.ted3x.evenline.presentation.auth.AuthFragment
import ge.ted3x.evenline.presentation.changepassword.ResetPasswordFragment
import ge.ted3x.evenline.presentation.forgotpassword.ForgotPasswordFragment
import ge.ted3x.evenline.presentation.onboarding.OnboardingFragment
import ge.ted3x.evenline.presentation.otp.OtpFragment
import ge.ted3x.evenline.presentation.signin.SignInFragment
import ge.ted3x.evenline.presentation.signup.SignUpFragment
import ge.ted3x.evenline.presentation.splash.SplashFragment

object Screens {

    fun Splash() = FragmentScreen { SplashFragment() }

    fun Onboarding() = FragmentScreen { OnboardingFragment() }

    fun Auth() = FragmentScreen { AuthFragment() }

    fun SignUp() = FragmentScreen { SignUpFragment() }

    fun SignIn() = FragmentScreen { SignInFragment() }

    fun ForgotPassword() = FragmentScreen { ForgotPasswordFragment() }

    fun ResetPassword(oobCode: String) = FragmentScreen {
        ResetPasswordFragment.newInstance(oobCode)
    }

    fun Otp() = FragmentScreen { OtpFragment() }

    fun Dashboard() = FragmentScreen { SignUpFragment() }
}
