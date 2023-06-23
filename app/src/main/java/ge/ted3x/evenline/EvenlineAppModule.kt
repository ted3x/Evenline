/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.ted3x.evenline.base.dispatcher.EvenlineCoroutineDispatchers
import ge.ted3x.evenline.base.dispatcher.EvenlineCoroutineDispatchersImpl
import ge.ted3x.evenline.data.SharedPreferencesHandler
import ge.ted3x.evenline.data.SharedPreferencesHandlerImpl
import ge.ted3x.evenline.data.UserRepositoryImpl
import ge.ted3x.evenline.domain.UserRepository
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
abstract class EvenlineAppModule {

    @Binds
    @Singleton
    abstract fun bindsSharedPreferencesHandler(impl: SharedPreferencesHandlerImpl): SharedPreferencesHandler

    @Binds
    @Singleton
    abstract fun bindEvenlineCoroutineDispatchers(impl: EvenlineCoroutineDispatchersImpl): EvenlineCoroutineDispatchers

    companion object {

        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth {
            return Firebase.auth
        }

        @Provides
        fun provideDispatcherMain(): CoroutineDispatcher {
            return Dispatchers.Main
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
