package com.combyne.uikit.components.edittext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.combyne.uikit.R
import com.combyne.uikit.components.edittext.utils.CurrencyWatcherUtils
import com.combyne.uikit.components.edittext.utils.CurrencyWatcherUtils.Companion.MALAYSIA_CURRENCY
import com.combyne.uikit.components.edittext.utils.DecimalDigitsInputFilter
import com.combyne.uikit.components.edittext.utils.PrefixWatcherUtils
import com.combyne.uikit.components.snackbar.SnackBarComponent
import com.combyne.uikit.components.snackbar.StateEnums
import com.combyne.uikit.databinding.ComponentEditTextBinding
import com.combyne.uikit.utils.CalendarUtil
import com.combyne.uikit.utils.CalendarUtil.Companion.DATE_FORMAT_MONTH_NAME_DAY_YEAR
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class EditTextComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), TextWatcher {

    companion object {
        const val DEFAULT = -1
        const val DATE = 0
        const val PASSWORD = 1
        const val AMOUNT = 2
        const val SEARCH = 3
        const val PHONE = 4
        const val COPY = 5
        const val GOLD = 6
    }

    private var binding: ComponentEditTextBinding =
        ComponentEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    var text: String? = ""
        get() {
            return if (type == AMOUNT)
                binding.editText.text.toString().replace(MALAYSIA_CURRENCY, "").replace(",", "")
            else
                binding.editText.text.toString()
        }
        set(value) {
            field = value
            if (field != "null") {
                binding.editText.setText(field)
                if (type == DATE)
                    initKind()
            }
        }
    var tag: String? = ""
        set(value) {
            field = value
            binding.editText.tag = field
            text = ""
        }

    private var maxLength: Int = 100
        set(value) {
            field = value
            binding.editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(field))
        }
    private var hint: String? = ""
    private var enabled: Boolean = true
    private var imeOptions: Int = EditorInfo.IME_ACTION_NEXT
    private var inputType: Int = InputType.TYPE_CLASS_TEXT
    private var type = DEFAULT
    private var drawableStart: Int? = null

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.EditTextComponent, 0, 0)
            text = typedArray.getString(R.styleable.EditTextComponent_android_text).toString()
            hint = typedArray.getString(R.styleable.EditTextComponent_android_hint)

            inputType =
                typedArray.getInt(R.styleable.EditTextComponent_android_inputType, inputType)
            imeOptions =
                typedArray.getInt(R.styleable.EditTextComponent_android_imeOptions, imeOptions)
            type = typedArray.getInt(R.styleable.EditTextComponent_textType, type)
            drawableStart =
                typedArray.getResourceId(R.styleable.EditTextComponent_android_drawableStart, 0)
            enabled = typedArray.getBoolean(R.styleable.EditTextComponent_android_enabled, enabled)

            typedArray.recycle()
            initView()
        }

    }

    private fun initView() {
        initInputText()
        initEditText()
        initKind()
    }

    private fun initKind() {
        when (type) {
            DATE -> {
                val drawable = ContextCompat.getDrawable(context, R.drawable.ic_calendar_today)
                binding.editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    binding.editText.compoundDrawablesRelative[0],
                    binding.editText.compoundDrawablesRelative[1],
                    drawable,
                    binding.editText.compoundDrawablesRelative[3]
                )

                val calendar = Calendar.getInstance()
                text?.let {
                    val year =
                        if (isEmpty()) calendar.get(Calendar.YEAR) else getYear()
                    val month =
                        if (isEmpty()) calendar.get(Calendar.MONTH) else getMonth()
                    val day = if (isEmpty()) calendar.get(Calendar.DAY_OF_MONTH) else getDay()
                    calendar.set(year, month, day)
                    val builder = MaterialDatePicker.Builder.datePicker()
                    builder.setSelection(calendar.timeInMillis)
                    val datePicker = builder.build()
                    datePicker.isCancelable = false
                    datePicker.addOnPositiveButtonClickListener {
                        val selectedCalendar = Calendar.getInstance()
                            .apply { timeInMillis = it }
                        binding.editText.setText(
                            CalendarUtil.calendarToStringDate(
                                inputDate = selectedCalendar,
                                toDateFormatter = DATE_FORMAT_MONTH_NAME_DAY_YEAR
                            )
                        )
                    }
                    binding.editText.isFocusableInTouchMode = false
                    binding.editText.isCursorVisible = false
                    binding.editText.setOnClickListener {
                        datePicker.show(
                            (context as AppCompatActivity).supportFragmentManager,
                            datePicker.toString()
                        )
                    }
                }
            }
            PHONE -> {
                maxLength = 14
                binding.textInput.counterMaxLength = 14
                binding.editText.inputType = InputType.TYPE_CLASS_NUMBER
                binding.editText.addTextChangedListener(PrefixWatcherUtils(binding.editText))
            }
            AMOUNT -> {
                binding.editText.inputType =
                    InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_DECIMAL
                binding.editText.addTextChangedListener(CurrencyWatcherUtils(binding.editText))
                text = "0"
            }
            SEARCH -> {
                val drawable = ContextCompat.getDrawable(context, R.drawable.ic_search)
                binding.editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    binding.editText.compoundDrawablesRelative[0],
                    binding.editText.compoundDrawablesRelative[1],
                    drawable,
                    binding.editText.compoundDrawablesRelative[3]
                )
                binding.editText.imeOptions = EditorInfo.IME_ACTION_SEARCH
            }
            PASSWORD ->
                binding.textInput.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            COPY -> {
                val drawable = ContextCompat.getDrawable(context, R.drawable.ic_copy)
                binding.editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    binding.editText.compoundDrawablesRelative[0],
                    binding.editText.compoundDrawablesRelative[1],
                    drawable,
                    binding.editText.compoundDrawablesRelative[3]
                )
                binding.editText.isFocusableInTouchMode = false
                binding.editText.isCursorVisible = false
                binding.editText.setOnClickListener {
                    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.addPrimaryClipChangedListener {
                        SnackBarComponent(
                            findViewById(SnackBarComponent.VIEW_PARENT),
                            "Copied to the Clipboard.",
                            StateEnums.SUCCESS
                        )
                    }
                    clipboard.setPrimaryClip(ClipData.newPlainText("Data", text))
                }
            }
            GOLD -> {
                binding.editText.inputType =
                    InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_DECIMAL
                val drawable = ContextCompat.getDrawable(context, R.drawable.ic_grams)
                binding.editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    binding.editText.compoundDrawablesRelative[0],
                    binding.editText.compoundDrawablesRelative[1],
                    drawable,
                    binding.editText.compoundDrawablesRelative[3]
                )
                binding.editText.filters = arrayOf<InputFilter>(
                    DecimalDigitsInputFilter(
                        digitsBeforeZero = 5,
                        digitsAfterZero = 3
                    )
                )
                text = "0"
            }
            else -> initEditText()
        }
    }

    private fun initInputText() {
        binding.textInput.hint = hint
        drawableStart?.let {
            if (it != 0)
                binding.textInput.startIconDrawable = ContextCompat.getDrawable(context, it)
        }
    }

    private fun initEditText() {
        binding.editText.setText(text)
        binding.editText.inputType = inputType
        binding.editText.imeOptions = imeOptions
        binding.editText.setLines(1)
        binding.editText.maxLines = 1
        binding.editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
        binding.editText.addTextChangedListener(this)
        isEnabled = enabled

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        binding.textInput.error = null
    }

    override fun afterTextChanged(s: Editable?) {
    }

    fun setError(error: String?) {
        error ?: return
        binding.textInput.error = error
    }

    override fun setEnabled(isEnabled: Boolean) {
        binding.editText.isFocusableInTouchMode = isEnabled
        binding.editText.isCursorVisible = isEnabled
    }

    fun isEmpty(): Boolean {
        return if (type == AMOUNT)
            text?.let { it.replace(",", "").toDouble() <= 0 } ?: false
        else
            binding.editText.text.toString().isEmpty()
    }

    fun replace(oldValue: String, newValue: String): String {
        return text?.replace(oldValue, newValue).toString()
    }

    fun setOnEditorActionListener(editTextListener: TextView.OnEditorActionListener) {
        binding.editText.setOnEditorActionListener(editTextListener)
    }

    fun addTextChangedListener(watcher: TextWatcher) {
        binding.editText.addTextChangedListener(watcher)
    }

    fun removeTextChangedListener(watcher: TextWatcher) {
        binding.editText.removeTextChangedListener(watcher)
    }

    /*
    * input 18-08-2009
    * output 2009
    * */
    private fun getYear(): Int {
        return Integer.parseInt(this.replace("-", "").substring(4, 8))
    }

    /*
    * input 18-08-2009
    * output 08
    * */
    private fun getMonth(): Int {
        return Integer.parseInt(this.replace("-", "").substring(2, 4)) - 1
    }

    /*
    * input 18-08-2009
    * output 18
    * */
    private fun getDay(): Int {
        return Integer.parseInt(this.replace("-", "").substring(0, 2))
    }

}