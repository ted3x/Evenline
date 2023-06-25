/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 11:40 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 11:40 PM
 */

package ge.ted3x.evenline.components

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.text.InputFilter
import android.util.AttributeSet
import android.view.Gravity
import androidx.core.content.withStyledAttributes
import androidx.core.widget.doAfterTextChanged
import ge.ted3x.evenline.R
import ge.ted3x.evenline.utils.EvenlineConstants.EVENLINE_OTP_MAX_LENGTH

class PinInputField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatEditText(context, attrs, defStyleAttr) {

    var listener: PinInputCompleteListener? = null
    val text get() = this.getText().toString()

    private val strokeWidth = context.resources.getDimension(R.dimen.dimen_p_1)
    private val currentSelection get() = if (hasFocus()) text.length else -1

    // width + radius ( 56 + 12 )
    private val defaultWidth = context.resources.getDimensionPixelSize(R.dimen.dimen_p_68)

    private val textPaint = Paint()
    private val normalFieldPaint = Paint()
    private val selectedFieldStrokePaint = Paint()
    private var maxLength = DEFAULT_MAX_LENGTH
        set(value) {
            field = value
            filters = arrayOf(InputFilter.LengthFilter(maxLength))
            invalidate()
        }

    init {

        context.withStyledAttributes(attrs, R.styleable.PinInputField, defStyleAttr, defStyleAttr) {
            withStyledResource()
        }

        normalFieldPaint.color = context.getColor(R.color.color_grayscale_50)

        with(selectedFieldStrokePaint) {
            isAntiAlias = true
            style = Paint.Style.STROKE
            color = context.getColor(R.color.color_primary_base)
            strokeWidth = this@PinInputField.strokeWidth
        }

        with(textPaint) {
            color = currentTextColor
            isAntiAlias = true
            textSize = this@PinInputField.textSize
            textAlign = Paint.Align.CENTER
            style = Paint.Style.FILL
            typeface = this@PinInputField.typeface
        }

        gravity = Gravity.CENTER_VERTICAL
        maxLines = 1
        isCursorVisible = false
        isSingleLine = true
        isClickable = false
        isFocusableInTouchMode = true
        background = null
        setWillNotDraw(false)
        requestFocus()

        doAfterTextChanged {
            if (it?.length == maxLength) listener?.onComplete(it.toString())
        }
    }

    private fun TypedArray.withStyledResource() {
        maxLength = getInteger(R.styleable.PinInputField_pinMaxLength, DEFAULT_MAX_LENGTH)
        val style = getResourceId(R.styleable.PinInputField_pinTextStyle, DEFAULT_STYLE_RES)
        setTextAppearance(style)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = getViewWidth(defaultWidth * maxLength, widthMeasureSpec)
        val singleFieldWidth = width / maxLength
        setMeasuredDimension(width, getViewHeight(singleFieldWidth, heightMeasureSpec))
    }

    private fun getViewWidth(desiredWidth: Int, widthMeasureSpec: Int): Int {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        // Measure Width
        return when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> desiredWidth.coerceAtMost(widthSize)
            MeasureSpec.UNSPECIFIED -> desiredWidth
            else -> desiredWidth
        }
    }

    private fun getViewHeight(desiredHeight: Int, heightMeasureSpec: Int): Int {
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // Measure Height
        return when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> desiredHeight.coerceAtMost(heightSize)
            MeasureSpec.UNSPECIFIED -> desiredHeight
            else -> desiredHeight
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        (0 until maxLength).forEach {
            val size = context.resources.getDimension(R.dimen.dimen_p_56)
            val cornerRadius = context.resources.getDimension(R.dimen.dimen_p_16)
            val padding = context.resources.getDimension(R.dimen.dimen_p_12)

            val left = (size * it) + padding * it + strokeWidth
            val right = left + size
            val top = padding / 2
            val bottom = size + top

            canvas.drawRoundRect(
                /* left = */ left,
                /* top = */ top,
                /* right = */ right,
                /* bottom = */ bottom,
                /* rx = */ cornerRadius,
                /* ry = */ cornerRadius,
                /* paint = */ if (currentSelection == it) selectedFieldStrokePaint else normalFieldPaint
            )
            if (text.isNotBlank()) {
                if (text.length > it) {
                    val char = text[it]
                    canvas.drawText(
                        char.toString(),
                        (left + (size / 2)),
                        ((height / 2) + top),
                        textPaint
                    )
                }
            }
        }
    }

    override fun onCheckIsTextEditor(): Boolean {
        return false
    }

    companion object {
        private const val DEFAULT_MAX_LENGTH = EVENLINE_OTP_MAX_LENGTH
        private const val DEFAULT_STYLE_RES = R.style.Label_Heading_4
    }
}

interface PinInputCompleteListener {

    fun onComplete(pin: String)
}
