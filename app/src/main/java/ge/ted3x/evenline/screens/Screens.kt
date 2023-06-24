/*
 * Created by Tedo Manvelidze(ted3x) on 6/24/23, 2:13 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/24/23, 1:27 PM
 */

package ge.ted3x.evenline.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ge.ted3x.evenline.presentation.auth.AuthFragment
import ge.ted3x.evenline.presentation.onboarding.OnboardingFragment
import ge.ted3x.evenline.presentation.signup.SignUpFragment
import ge.ted3x.evenline.presentation.splash.SplashFragment

object Screens {

    fun Splash() = FragmentScreen { SplashFragment() }

    fun Onboarding() = FragmentScreen { OnboardingFragment() }

    fun Auth() = FragmentScreen { AuthFragment() }

    fun SignUp() = FragmentScreen { SignUpFragment() }

    fun Dashboard() = FragmentScreen { SignUpFragment() }
}
