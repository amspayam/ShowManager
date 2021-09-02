package com.combyne.uikit.components.edittext.utils

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

/**
 * Created by p.kokabi on 3/27/17.
 */

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CurrencyWatcherUtils(private val editText: AppCompatEditText) : TextWatcher {

    private val locale = Locale("ms", "MY", MALAYSIA_CURRENCY)

    companion object {
        const val MALAYSIA_CURRENCY = "MYR"
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(editable: Editable) {
        if (editText.text == null) return
        val parsed = parseToBigDecimal(editable.toString()) ?: return
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.minimumFractionDigits = 2
        formatter.currency = Currency.getInstance(locale)
        val formatted = formatter.format(parsed)

        editText.removeTextChangedListener(this)
        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)
    }

    private fun parseToBigDecimal(value: String): BigDecimal? {
        val replaceable =
            String.format(
                "[%s,.\\s]",
                NumberFormat.getCurrencyInstance(locale).currency.symbol
            )
        val cleanString = value.replace(replaceable.toRegex(), "")
        return cleanString.toBigDecimalOrNull()
            ?.setScale(2, BigDecimal.ROUND_FLOOR)
            ?.divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
    }

}
