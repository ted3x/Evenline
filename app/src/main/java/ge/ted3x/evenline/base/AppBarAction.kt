/*
 * Created by Tedo Manvelidze(ted3x) on 6/22/23, 9:59 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/22/23, 9:42 PM
 */

package ge.ted3x.evenline.base

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import com.google.android.material.button.MaterialButton
import ge.ted3x.evenline.R

sealed class AppBarAction {

    abstract fun getView(context: Context): View
    data class Icon(@DrawableRes val iconRes: Int, val onClick: () -> Unit) : AppBarAction() {
        override fun getView(context: Context): View {
            return MaterialButton(context, null, R.attr.appBarActionButtonStyle).apply {
                setOnClickListener { onClick.invoke() }
                setIconResource(iconRes)
                iconSize = context.resources.getDimensionPixelSize(R.dimen.dimen_p_24)
            }
        }
    }

    data class Text(
        val text: String,
        @StyleRes val textStyle: Int,
        @ColorRes val textColorRes: Int,
        val onClick: () -> Unit
    ) : AppBarAction() {
        override fun getView(context: Context): View {
            return TextView(context).apply {
                text = this@Text.text
                setTextAppearance(textStyle)
                setTextColor(context.getColor(textColorRes))
                setOnClickListener { onClick.invoke() }
            }
        }
    }
}
