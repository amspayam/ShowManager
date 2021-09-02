package com.combyne.uikit.components.edittext.utils

import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import com.combyne.uikit.extension.permitive.string.add

/**
 * Created by p.kokabi on 3/27/17.
 */

class PrefixWatcherUtils(private val editText: AppCompatEditText) : TextWatcher {

    private var isRunning = false
    private var isDeleting = false

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        val mask = if (editText.tag == null) "+60" else editText.tag.toString()

        if (isRunning || isDeleting) {
            return
        }

        isRunning = true

        if (!editable.toString().startsWith(mask.trim { it <= ' ' })) {
            editText.setText(mask.add(editable.toString()))
            Selection.setSelection(editText.text, editText.text!!.length)
        }

        isRunning = false
    }

}
