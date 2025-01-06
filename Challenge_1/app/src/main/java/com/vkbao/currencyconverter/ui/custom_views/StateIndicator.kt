package com.vkbao.currencyconverter.ui.custom_views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.vkbao.currencyconverter.R

class StateIndicator (
    context: Context,
    attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {
    private val view: View
    private var _state: State
    private var _loadingText: String
    private var _errorText: String
    private var _successText: String
    private var _animationDuration: Long
    private var _disappearAfter: Long

    var state: State
        get() = _state
        set(value) {
            _state = value
            changeState(value)
        }

    enum class State {
        LOADING,
        ERROR,
        SUCCESS
    }

    init {
        attributeSet.apply {
            val typedArr = context.obtainStyledAttributes(this, R.styleable.CircularKey, 0, 0)
            _loadingText = typedArr.getString(R.styleable.StateIndicator_loading_text) ?: "Loading"
            _errorText = typedArr.getString(R.styleable.StateIndicator_error_text) ?: "Error"
            _successText = typedArr.getString(R.styleable.StateIndicator_success_text) ?: "Success"
            _animationDuration =
                typedArr.getInteger(R.styleable.StateIndicator_animate_duration, 900).toLong()
            _disappearAfter = typedArr.getInteger(R.styleable.StateIndicator_disappear_after_success_duration, 3000).toLong()

            typedArr.recycle()
        }

        view = LayoutInflater.from(context).inflate(R.layout.state_indicator, this, true)
        _state = State.LOADING

        changeState(_state)
    }

    private fun changeState(state: State) {
        val stateView = view.findViewById<LinearLayout>(R.id.state_indicator)
        val indicatorText = stateView.findViewById<TextView>(R.id.state_indicator_text)
        val indicatorIcon = stateView.findViewById<ImageView>(R.id.state_indicator_icon)
        val loadingProgressbar = stateView.findViewById<ProgressBar>(R.id.progressBar)
        val reloadContainer = view.findViewById<LinearLayout>(R.id.reload_container)

        when (state) {
            State.LOADING -> {
                indicatorText.text = _loadingText
                indicatorIcon.visibility = View.GONE
                loadingProgressbar.visibility = View.VISIBLE
                stateView.visibility = View.VISIBLE
                reloadContainer.visibility = View.GONE

            }
            State.ERROR -> {
                indicatorText.text = _errorText
                indicatorIcon.setImageResource(R.drawable.error)
                indicatorIcon.visibility = View.VISIBLE
                loadingProgressbar.visibility = View.GONE
                reloadContainer.visibility = View.VISIBLE
                stateView.visibility = View.VISIBLE
            }
            State.SUCCESS -> {
                indicatorText.text = _successText
                indicatorIcon.setImageResource(R.drawable.success)
                indicatorIcon.visibility = View.VISIBLE
                loadingProgressbar.visibility = View.GONE
                reloadContainer.visibility = View.GONE
                stateView.visibility = View.VISIBLE

                postDelayed({
                    disappearToLeft()
                    stateView.visibility = View.GONE
                    reloadContainer.visibility = View.VISIBLE
                    appearFromLeft()
                }, _disappearAfter)
            }
        }

        invalidate()
    }

    fun setOnClickReloadListener(l: View.OnClickListener?) {
        val reloadContainer = view.findViewById<LinearLayout>(R.id.reload_container)
        reloadContainer.setOnClickListener(l)
    }

    private fun disappearToLeft() {
        val translationX = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 0f, -width.toFloat())
        val alpha = ObjectAnimator.ofFloat(this, View.ALPHA, 1f, 0f)

        AnimatorSet().apply {
            playTogether(translationX, alpha)
            duration = _animationDuration
            start()
        }
    }

    private fun appearFromLeft() {
        translationX = -width.toFloat()
        alpha = 0f

        val translationX = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, -width.toFloat(), 0f)
        val alpha = ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f)

        AnimatorSet().apply {
            playTogether(translationX, alpha)
            duration = _animationDuration
            start()
        }
    }
}