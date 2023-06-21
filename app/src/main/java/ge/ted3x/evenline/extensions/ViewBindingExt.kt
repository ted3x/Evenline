/*
 * Created by Tedo Manvelidze(ted3x) on 6/21/23, 11:28 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/21/23, 11:28 PM
 */

package ge.ted3x.evenline.extensions

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : ViewBinding> Fragment.viewBinding(factory: (View) -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {
        private var binding: T? = null

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
            binding ?: factory(requireView()).also {
                // if binding is accessed after Lifecycle is DESTROYED, create new instance, but don't cache it
                if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                    viewLifecycleOwner.lifecycle.addObserver(this)
                    binding = it
                }
            }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }
    }

/**
 * Example usage on Activity (sp_fragment_example_layout.xml)
 * `val bind by viewBinding(ActivityExampleLayoutBinding::inflate) and in setContentView(bind.root)`
 */

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline factory: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }
