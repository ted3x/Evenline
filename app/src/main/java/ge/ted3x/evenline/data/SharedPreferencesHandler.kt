/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:38 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 4:40 PM
 */

package ge.ted3x.evenline.data

interface SharedPreferencesHandler {

    fun writeString(key: String, value: String)
    fun readString(key: String): String?
    fun writeBoolean(key: String, value: Boolean)
    fun readBoolean(key: String): Boolean
}
