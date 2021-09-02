package com.combyne.uikit.base.adapter

import android.view.View

/**
 * Created by Rasoul Miri on 12/05/2021
 */

interface OnClickListenerMaster {
    fun onClick(position: Int, itemData: Any? = null, viewId: View? = null)
}