package com.combyne.uikit.components.dropdown

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.combyne.uikit.R
import com.combyne.uikit.components.dropdown.model.DropDownModel
import com.combyne.uikit.databinding.ComponentDropDownBinding

class DropDownComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: ComponentDropDownBinding =
        ComponentDropDownBinding.inflate(LayoutInflater.from(context), this, true)
    var text: String? = ""
        get() = binding.spinnerDropDown.text.toString()
        set(value) {
            field = value
            if (field != "null")
                binding.spinnerDropDown.setText(field)
        }
    private var hint: String? = null
    private var drawableStart: Int? = null
    private var itemSelected: DropDownModel? = null
    private var list = listOf<DropDownModel>()

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.DropDownComponent, 0, 0)
            text = typedArray.getString(R.styleable.DropDownComponent_android_text).toString()
            hint = typedArray.getString(R.styleable.DropDownComponent_android_hint)
            drawableStart =
                typedArray.getResourceId(R.styleable.DropDownComponent_android_drawableStart, 0)

            typedArray.recycle()
            initView()
        }

    }

    private fun initView() {
        initInputText()
        initEditText()
    }

    private fun initInputText() {
        hint?.let {
            binding.textInput.hint = it
        }
        if (drawableStart != null && drawableStart != 0)
            binding.textInput.startIconDrawable =
                ContextCompat.getDrawable(context, drawableStart!!)
    }

    private fun initEditText() {
        binding.spinnerDropDown.setText(text)
    }

    fun setData(list: MutableList<DropDownModel>) {
        this.list = list
        val titles: List<String> = list.map { it.title }
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_dropdown_item_1line,
            titles
        )
        binding.spinnerDropDown.setAdapter(adapter)
        binding.spinnerDropDown.setOnItemClickListener { _, _, position, _ ->
            itemSelected = list[position]
            binding.textInput.error = null
        }
    }

    fun select(id: String?) {
        val model = list.find { it.data == id }
        text = model?.title
        itemSelected = model
    }

    fun getItemSelect(): DropDownModel? {
        return itemSelected
    }

    fun setError(error: String?) {
        binding.textInput.error = error
    }

    fun isEmpty() = binding.spinnerDropDown.text.toString().isEmpty()

}