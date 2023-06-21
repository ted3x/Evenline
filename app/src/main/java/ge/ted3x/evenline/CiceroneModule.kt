/*
 * Created by Tedo Manvelidze(ted3x) on 6/21/23, 11:28 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/21/23, 11:28 PM
 */

package ge.ted3x.evenline

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CiceroneModule {

    @Provides
    @Singleton
    fun provideCicerone() = Cicerone.create()

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>) = cicerone.router
}
