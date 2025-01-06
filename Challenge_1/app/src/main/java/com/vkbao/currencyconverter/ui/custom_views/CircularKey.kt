package com.vkbao.currencyconverter.ui.custom_views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.vkbao.currencyconverter.R

class CircularKey(
    context: Context,
    attributeSet: AttributeSet,
) : FrameLayout(context, attributeSet) {
    private var _text: String
    val text get() = _text

    private var _textSize: Float
    private var _textColor: Int
    private val view: View

    init {
        isClickable = true
        isFocusable = true

        attributeSet.apply {
            val typedArr = context.obtainStyledAttributes(this, R.styleable.CircularKey, 0, 0)
            _text = typedArr.getString(R.styleable.CircularKey_text) ?: ""
            _textSize = typedArr.getDimension(R.styleable.CircularKey_textSize, 24f)
            _textColor = typedArr.getColor(R.styleable.CircularKey_textColor, context.getColor(R.color.description_color))

            typedArr.recycle()
        }

        view = LayoutInflater.from(context).inflate(R.layout.circular_key, this, true)

        view.findViewById<TextView>(R.id.key_text).apply {
            text = _text
            setTextSize(TypedValue.COMPLEX_UNIT_PX, _textSize)
            setTextColor(this@CircularKey._textColor)
            isClickable = false
            isFocusable = false
            isFocusableInTouchMode = false
        }

        invalidate()
    }
}