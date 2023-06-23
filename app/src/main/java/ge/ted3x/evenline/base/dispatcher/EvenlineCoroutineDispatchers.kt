/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */package ge.ted3x.evenline.base.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface EvenlineCoroutineDispatchers {

    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}
