/*
 * Created by Tedo Manvelidze(ted3x) on 6/21/23, 11:28 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/21/23, 11:28 PM
 */

package ge.ted3x.evenline.presentation.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import ge.ted3x.evenline.R

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {
    @Provides
    fun provideNavigator(@ActivityContext context: Context) =
        AppNavigator(context as AppCompatActivity, R.id.container)
}
