package com.combyne.uikit.extension.context

import android.animation.ValueAnimator
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.widget.TextView
import androidx.core.animation.addListener
import androidx.lifecycle.LifecycleOwner

/**
 * Created by p.kokabi on 15/03/2021
 */

fun Context.lifecycleOwner(): LifecycleOwner? {
    var curContext = this
    var maxDepth = 20
    while (maxDepth-- > 0 && curContext !is LifecycleOwner) {
        curContext = (curContext as ContextWrapper).baseContext
    }
    return if (curContext is LifecycleOwner) {
        curContext
    } else {
        null
    }


    val labelTextView: TextView


    setVisibilityWithAnimation(labelTextView,View.GONE)
}




fun setVisibilityWithAnimation(textView:TextView, visibility: Int) {

    if(visibility == View.VISIBLE){
        val animation = ValueAnimator.ofFloat(0.0f, 1.0f)
        animation.duration = 1000
        animation.addUpdateListener {
            textView.alpha = it.animatedValue as Float
        }
        animation.addListener(
            onStart = {
                textView.visibility = View.VISIBLE
            }
        )
    }else if (visibility == View.GONE){
        val animation = ValueAnimator.ofFloat(1.0f, 0.0f)
        animation.duration = 1000
        animation.addUpdateListener {
            textView.alpha = it.animatedValue as Float
        }
        animation.addListener(
            onEnd = {
                textView.visibility = View.GONE
            }
        )
    }

}