/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 4:42 PM
 */

package ge.ted3x.evenline

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.ted3x.evenline.data.SharedPreferencesHandler
import ge.ted3x.evenline.data.SharedPreferencesHandlerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EvenlineAppModule {

    @Binds
    @Singleton
    abstract fun bindsSharedPreferencesHandler(impl: SharedPreferencesHandlerImpl): SharedPreferencesHandler
}
