/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:30 PM
 */package ge.ted3x.evenline.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import ge.ted3x.evenline.presentation.activity.MainActivity
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel>(private val layoutRes: Int) : Fragment(layoutRes) {

    abstract val viewModelClass: KClass<VM>
    lateinit var viewModel: VM
        private set

    open fun getAppBarTitleRes(): Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass.java]
        (requireActivity() as MainActivity).setupAppBar(
            getAppBarTitleRes(),
            getAppBarActions()?.map { it.getView(requireContext()) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindViewModel(viewModel)
        viewModel.snackBarMessage.collectFlow {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }

    abstract fun onBindViewModel(vm: VM)

    open fun getAppBarActions(): List<AppBarAction>? = null

    protected fun <T> Flow<T>.collectFlow(block: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                this@collectFlow.collectLatest { block.invoke(it) }
            }
        }
    }
}
