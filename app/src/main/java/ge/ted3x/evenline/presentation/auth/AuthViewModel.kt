/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:39 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 5:39 PM
 */

package ge.ted3x.evenline.presentation.auth

import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.ted3x.evenline.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val router: Router) : BaseViewModel()
