package com.combyne.uikit.base.adapter

import android.view.View

interface OnClickListenerMaster {
    fun onClick(position: Int, itemData: Any? = null, viewId: View? = null)
}