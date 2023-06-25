/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 5:54 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 5:40 PM
 */

package ge.ted3x.evenline.extensions

import android.graphics.Typeface
import android.text.Spannable
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import ge.ted3x.evenline.R

fun TextView.spannableClick(
    spannableClick: (() -> Unit?)? = null,
    textClick: (() -> Unit?)? = null,
    isUnderLine: Boolean? = false,
    spannableColor: Int = ContextCompat.getColor(context, R.color.color_primary_base),
    backGroundColor: Int = ContextCompat.getColor(context, R.color.color_white),
    typeface: Int = Typeface.BOLD
) {
    var spannedText = this.text.toString()
    val startIndex = spannedText.indexOf("[")
    val endIndex = spannedText.indexOf("]")

    this.movementMethod = LinkMovementMethod.getInstance()

    spannedText = spannedText.replace("[", "")
    spannedText = spannedText.replace("]", "")

    this.setText(spannedText, TextView.BufferType.SPANNABLE)
    val spannable = this.text as Spannable

    spannable.setSpan(
        object : ClickableSpan() {
            override fun onClick(v: View) {
                spannableClick?.invoke()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.typeface = Typeface.create(ds.typeface, typeface)
                ds.color = spannableColor
                ds.bgColor = backGroundColor
                ds.isUnderlineText = isUnderLine!!
            }
        },
        startIndex,
        endIndex - 1,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    this.setOnClickListener { textClick?.invoke() }
}
