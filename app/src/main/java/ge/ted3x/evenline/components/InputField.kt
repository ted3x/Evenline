/*
 * Created by Tedo Manvelidze(ted3x) on 6/25/23, 8:15 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 6/25/23, 8:14 PM
 */

package ge.ted3x.evenline.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.doOnTextChanged
import ge.ted3x.evenline.R
import ge.ted3x.evenline.components.InputFieldDrawableClickListener.DrawablePosition

class InputField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {

    val text get() = this.getText().toString()

    private var drawableRight: Drawable? = null
    private var drawableLeft: Drawable? = null
    private var drawableTop: Drawable? = null
    private var drawableBottom: Drawable? = null

    private var actionX = 0
    private var actionY = 0

    private var clickListener: InputFieldDrawableClickListener? = null

    private var isError = false

    init {
        doOnTextChanged { _, _, _, _ ->
            setErrorState(false)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Get the drawables set for each direction
        val drawables = compoundDrawables
        drawableLeft = drawables[0]
        drawableTop = drawables[1]
        drawableRight = drawables[2]
        drawableBottom = drawables[3]
    }

    override fun setCompoundDrawables(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        if (left != null) {
            drawableLeft = left
        }
        if (right != null) {
            drawableRight = right
        }
        if (top != null) {
            drawableTop = top
        }
        if (bottom != null) {
            drawableBottom = bottom
        }
        super.setCompoundDrawables(left, top, right, bottom)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var bounds: Rect?
        if (event.action == MotionEvent.ACTION_DOWN) {
            actionX = event.x.toInt()
            actionY = event.y.toInt()
            if (drawableBottom != null &&
                drawableBottom!!.bounds.contains(actionX, actionY)
            ) {
                clickListener?.onClick(DrawablePosition.BOTTOM)
                return super.onTouchEvent(event)
            }
            if (drawableTop != null &&
                drawableTop!!.bounds.contains(actionX, actionY)
            ) {
                clickListener?.onClick(DrawablePosition.TOP)
                return super.onTouchEvent(event)
            }

            // this works for left since container shares 0,0 origin with bounds
            if (drawableLeft != null) {
                bounds = drawableLeft!!.bounds
                var x: Int
                var y: Int
                val extraTapArea = (13 * resources.displayMetrics.density + 0.5).toInt()
                x = actionX
                y = actionY
                if (!bounds.contains(actionX, actionY)) {
                    /** Gives the +20 area for tapping.  */
                    x = (actionX - extraTapArea)
                    y = (actionY - extraTapArea)
                    if (x <= 0) x = actionX
                    if (y <= 0) y = actionY
                    /** Creates square from the smallest value  */
                    if (x < y) {
                        y = x
                    }
                }
                if (bounds.contains(x, y)) {
                    clickListener?.onClick(DrawablePosition.LEFT)
                    event.action = MotionEvent.ACTION_CANCEL
                    return false
                }
            }
            if (drawableRight != null) {
                bounds = drawableRight!!.bounds
                var x: Int
                var y: Int
                val extraTapArea = (15 * resources.displayMetrics.density + 0.5).toInt()
                /**
                 * IF USER CLICKS JUST OUT SIDE THE RECTANGLE OF THE DRAWABLE
                 * THAN ADD X AND SUBTRACT THE Y WITH SOME VALUE SO THAT AFTER
                 * CALCULATING X AND Y CO-ORDINATE LIES INTO THE DRAWBABLE
                 * BOUND. - this process help to increase the tappable area of
                 * the rectangle.
                 */
                x = (actionX + extraTapArea)
                y = (actionY - extraTapArea)
                /**Since this is right drawable subtract the value of x from the width
                 * of view. so that width - tappedarea will result in x co-ordinate in drawable bound.
                 */
                x = width - x

                /*x can be negative if user taps at x co-ordinate just near the width.
                 * e.g views width = 300 and user taps 290. Then as per previous calculation
                 * 290 + 13 = 303. So subtract X from getWidth() will result in negative value.
                 * So to avoid this add the value previous added when x goes negative.
                 */if (x <= 0) {
                    x += extraTapArea
                }

                /* If result after calculating for extra tappable area is negative.
                 * assign the original value so that after subtracting
                 * extratapping area value doesn't go into negative value.
                 */if (y <= 0) y = actionY
                /**If drawble bounds contains the x and y points then move ahead. */
                if (bounds.contains(x, y)) {
                    onRightDrawableClick()
                    clickListener?.onClick(DrawablePosition.RIGHT)
                    event.action = MotionEvent.ACTION_CANCEL
                    return false
                }
                return super.onTouchEvent(event)
            }
        }
        return super.onTouchEvent(event)
    }

    fun setDrawableClickListener(listener: InputFieldDrawableClickListener?) {
        clickListener = listener
    }

    fun setErrorState(isError: Boolean) {
        this.isError = isError
        background = if (isError) {
            context.getDrawable(R.drawable.selector_edit_text_error)
        } else {
            context.getDrawable(R.drawable.selector_edit_text)
        }
    }

    private fun onRightDrawableClick() {
        when (inputType) {
            InputType.TYPE_CLASS_TEXT.or(InputType.TYPE_TEXT_VARIATION_PASSWORD) -> {
                setInputTypeWithPreserving(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
            }
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD -> {
                setInputTypeWithPreserving(
                    InputType.TYPE_CLASS_TEXT.or(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                )
            }
            else -> Unit
        }
    }

    private fun setInputTypeWithPreserving(inputType: Int) {
        val selectionStart = selectionStart
        val selectionEnd = selectionEnd
        val typeFace = typeface
        this.inputType = inputType
        setSelection(selectionStart, selectionEnd)
        typeface = typeFace
    }
}

interface InputFieldDrawableClickListener {
    enum class DrawablePosition {
        TOP, BOTTOM, LEFT, RIGHT
    }

    fun onClick(target: DrawablePosition?)
}
