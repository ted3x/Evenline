/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:06 PM
 */

package ge.ted3x.evenline.presentation.signup

import androidx.annotation.CallSuper
import com.github.terrakok.cicerone.Router
import ge.ted3x.evenline.domain.SignUpInteractor
import ge.ted3x.evenline.presentation.signup.state.SignUpUiState
import io.kotest.common.runBlocking
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.exhaustive
import io.mockk.*
import io.mockk.impl.annotations.MockK
import java.util.regex.Pattern
import kotlinx.coroutines.test.StandardTestDispatcher
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class SignUpViewModelTest {

    @MockK
    private lateinit var router: Router

    @MockK
    private lateinit var signUpInteractor: SignUpInteractor

    private lateinit var vm: SignUpViewModel

    private fun getViewModel() = SignUpViewModel(StandardTestDispatcher(), signUpInteractor, router)

    @Before
    @CallSuper
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun finish() {
        unmockkAll()
    }

    @Test
    fun `onSignUp when fields are empty`() {
        val emptyFullNameCase = SignUpValidationParams(
            fullName = "",
            expectedUiState = SignUpUiState(
                isFullNameValid = false,
                errorMessage = "Enter your name"
            )
        )
        val emptyEmailCase = SignUpValidationParams(
            fullName = "test",
            email = "",
            expectedUiState = SignUpUiState(isEmailValid = false, errorMessage = "Enter your email")
        )
        val emptyPasswordCase = SignUpValidationParams(
            fullName = "test",
            email = "test",
            password = "",
            expectedUiState = SignUpUiState(
                isPasswordValid = false,
                errorMessage = "Enter your password"
            )
        )
        val variants =
            listOf(emptyFullNameCase, emptyEmailCase, emptyPasswordCase).exhaustive()
        runBlocking {
            variants.checkAll {
                every { (any<Pattern>().matcher(it.email).matches()) } returns true
                val vm = getViewModel()
                vm.onSignUp(fullName = it.fullName, email = it.email, password = it.password)
                vm.uiState.value isTheSame it.expectedUiState
            }
        }
    }

    infix fun <T> T?.isTheSame(value: T?) {
        MatcherAssert.assertThat(this, CoreMatchers.equalTo(value))
    }

    infix fun <T, K : Any> T?.isNotTheSame(value: K?) {
        MatcherAssert.assertThat(this, CoreMatchers.not(CoreMatchers.equalTo(value)))
    }

    data class SignUpValidationParams(
        val fullName: String = "",
        val email: String = "",
        val password: String = "",
        val expectedUiState: SignUpUiState
    )
}
