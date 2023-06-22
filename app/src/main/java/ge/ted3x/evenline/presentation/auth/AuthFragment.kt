/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:39 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 5:39 PM
 */

package ge.ted3x.evenline.presentation.auth

import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.R
import ge.ted3x.evenline.base.BaseFragment

@AndroidEntryPoint
class AuthFragment : BaseFragment<AuthViewModel>(R.layout.fragment_auth) {

    override val viewModelClass = AuthViewModel::class
    override fun onBindViewModel(vm: AuthViewModel) {
    }
}
