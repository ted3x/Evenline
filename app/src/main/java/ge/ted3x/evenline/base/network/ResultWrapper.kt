/*
 * Created by Tedo Manvelidze(ted3x) on 6/23/23, 5:16 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/23/23, 5:16 PM
 */

package ge.ted3x.evenline.base.network

sealed class ResultWrapper<out T> {
    object Loading : ResultWrapper<Nothing>()
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error(val exception: EvenlineException) : ResultWrapper<Nothing>()

    companion object {
        fun loading() = Loading
        fun <T> success(data: T) = Success(data)
        fun error(exception: EvenlineException) = Error(exception)
    }
}
