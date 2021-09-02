package com.combyne.uikit.components.button

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.combyne.uikit.R
import com.combyne.uikit.extension.permitive.dpToPx
import com.combyne.uikit.extension.permitive.string.pascalCase
import com.combyne.uikit.extension.view.gone
import com.combyne.uikit.extension.view.visible
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ButtonComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    // <editor-fold defaultState="collapsed" desc="Variables">

    companion object {
        const val FILL_BUTTON = 0
        const val OUT_LINE_BUTTON = 1
        const val TEXT_BUTTON = 2
    }

    var text: String? = ""
    private var buttonType = FILL_BUTTON
    private lateinit var progressBar: ProgressBar
    private lateinit var button: TextView

    // </editor-fold>

    init {
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ButtonComponent, 0, 0)
            text = typedArray.getString(R.styleable.ButtonComponent_android_text).toString()
            buttonType = typedArray.getInt(R.styleable.ButtonComponent_buttonType, FILL_BUTTON)
            typedArray.recycle()
            initView()
        }
    }

    private fun initView() {

        when (buttonType) {
            FILL_BUTTON -> button = MaterialButton(context, null, R.attr.filledButton)
            OUT_LINE_BUTTON ->
                button = MaterialButton(context, null, R.attr.outlineButton)
            TEXT_BUTTON ->
                button = MaterialButton(context, null, R.attr.textButton)
        }
        button.text = text?.pascalCase()

        progressBar = ProgressBar(context)
        progressBar.indeterminateTintList = ColorStateList.valueOf(button.textColors.defaultColor)
        progressBar.isIndeterminate = true
        progressBar.elevation = 8.dpToPx(context).toFloat()
        progressBar.scaleX = 0.5f
        progressBar.scaleY = 0.5f
        progressBar.gone()

        addView(button)
        addView(progressBar)
    }

    fun startLoading() {
        isEnabled = false
        progressBar.visible()
        button.text = ""
    }

    fun stopLoading() {
        GlobalScope.launch(context = Dispatchers.IO) {
            delay(500)
            launch(context = Dispatchers.Main) {
                isEnabled = true
                progressBar.gone()
                button.text = text?.pascalCase()
            }
        }

    }
}