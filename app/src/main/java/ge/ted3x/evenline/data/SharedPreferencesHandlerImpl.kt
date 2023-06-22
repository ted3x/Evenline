/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 5:38 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 4:40 PM
 */

package ge.ted3x.evenline.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ge.ted3x.evenline.utils.EvenlineConstants
import javax.inject.Inject

class SharedPreferencesHandlerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) :
    SharedPreferencesHandler {

    private val sharedPreferences =
        context.getSharedPreferences(
            EvenlineConstants.EVENLINE_SHARED_PREFERENCES_KEY,
            Context.MODE_PRIVATE
        )

    override fun writeString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun readString(key: String) = sharedPreferences.getString(key, null)

    override fun writeBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun readBoolean(key: String) = sharedPreferences.getBoolean(key, false)
}
