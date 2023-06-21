/*
 * Created by Tedo Manvelidze(ted3x) on 6/21/23, 11:28 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/21/23, 11:28 PM
 */

package ge.ted3x.evenline.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel>(private val layoutRes: Int) : Fragment(layoutRes) {

    abstract val viewModelClass: KClass<VM>
    lateinit var viewModel: VM
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }
}
