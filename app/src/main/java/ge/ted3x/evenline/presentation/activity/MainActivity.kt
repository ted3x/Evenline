/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 5:40 PM
 */

package ge.ted3x.evenline.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import ge.ted3x.evenline.databinding.ActivityMainBinding
import ge.ted3x.evenline.extensions.viewBinding
import ge.ted3x.evenline.screens.Screens
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var navigatorHolder: NavigatorHolder

    @Inject lateinit var navigator: AppNavigator

    @Inject lateinit var router: Router

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.addOnBackStackChangedListener {
            binding.appbarBackButton.visibility = if (supportFragmentManager.backStackEntryCount > 1) View.VISIBLE else View.GONE
        }
        binding.appbarBackButton.setOnClickListener { router.exit() }
        router.newRootScreen(Screens.Splash())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    fun setupAppBar(@StringRes titleRes: Int?, appBarActionViews: List<View>?) {
        if (titleRes != null) {
            binding.appbarTitle.visibility = View.VISIBLE
            binding.appbarTitle.setText(titleRes)
        } else {
            binding.appbarTitle.visibility = View.GONE
        }
        binding.appbarActions.removeAllViews()
        appBarActionViews?.forEach {
            binding.appbarActions.addView(it)
        }
    }
}
