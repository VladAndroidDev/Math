package com.v.nevi.p.sv.android.math.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.v.nevi.p.sv.android.math.R

private const val LENGTH_MAX_VALUE=12
class CalculatorView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : TableLayout(context, attributeSet) {

    var onEqualClickListener: OnEqualMarkClickListener? = null


    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        0
    )

    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        0
    )

    constructor(context: Context) : this(
        context,
        null
    )

    private lateinit var answer: TextView
    private lateinit var removeButton: Button
    private lateinit var buttonEqual: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonFour: Button
    private lateinit var buttonFive: Button
    private lateinit var buttonSix: Button
    private lateinit var buttonSeven: Button
    private lateinit var buttonEight: Button
    private lateinit var buttonNine: Button
    private lateinit var buttonZero: Button

    private var value: String = ""

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.calculator_view, this, true)
        initViews()
        initializeListeners()
        //initializeAttributes(attributeSet,defStyleAttr,defStyleRes)
    }

    private fun initViews() {
        answer = findViewById(R.id.answer)
        removeButton = findViewById(R.id.button_remove)
        buttonEqual = findViewById(R.id.button_equal)
        buttonMinus = findViewById(R.id.button_minus)
        buttonOne= findViewById(R.id.button_one)
        buttonTwo= findViewById(R.id.button_two)
        buttonThree = findViewById(R.id.button_three)
        buttonFour = findViewById(R.id.button_four)
        buttonFive = findViewById(R.id.button_five)
        buttonSix = findViewById(R.id.button_six)
        buttonSeven = findViewById(R.id.button_seven)
        buttonEight = findViewById(R.id.button_eight)
        buttonNine= findViewById(R.id.button_nine)
        buttonZero= findViewById(R.id.button_zero)
    }

//    private fun initializeAttributes(attributeSet: AttributeSet?,defStyleAttr: Int,defStyleRes: Int){
//        if (attributeSet == null) return
//        val typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.CalculatorView,defStyleAttr,defStyleRes)
//        onEqualClickListener = typedArray.getResourceIdOrThrow(R.styleable.CalculatorView_listenerOnEqualClick) as OnEqualMarkClickListener
//
//        typedArray.recycle()
//    }

    private fun onNumberClick(newValue:String){
        if(value=="0") return
        if(value == context.getString(R.string.enter_the_number)){
            value=""
        }
        if(value.length== LENGTH_MAX_VALUE) return
        value += newValue
        updateText()
    }
    private fun initializeListeners() {
        removeButton.setOnClickListener {
            if(value==context.getString(R.string.enter_the_number)) return@setOnClickListener
            if (value.isNotEmpty()) {
                value = if (value.length == 1) {
                    context.getString(R.string.enter_the_number)
                } else {
                    value.substring(0, value.length - 1)
                }
            }
            updateText()
        }
        buttonOne.setOnClickListener {
            onNumberClick("1")
        }
        buttonTwo.setOnClickListener {
            onNumberClick("2")
        }
        buttonThree.setOnClickListener {
            onNumberClick("3")
        }
        buttonFour.setOnClickListener {
            onNumberClick("4")
        }
        buttonFive.setOnClickListener {
            onNumberClick("5")
        }
        buttonSix.setOnClickListener {
            onNumberClick("6")
        }
        buttonSeven.setOnClickListener {
            onNumberClick("7")
        }
        buttonEight.setOnClickListener {
            onNumberClick("8")
        }
        buttonNine.setOnClickListener {
            onNumberClick("9")
        }
        buttonZero.setOnClickListener {
            onNumberClick("0")
        }
        buttonMinus.setOnClickListener {
            value = when {
                value.isEmpty()||value==context.getString(R.string.enter_the_number) -> {
                    "-"
                }
                value[0] == '-' -> {
                    value.substring(1, value.length)
                }
                else -> {
                    "-$value"
                }
            }
            updateText()
        }
        buttonEqual.setOnClickListener {
            if (value.isEmpty() || value == "-" || value==context.getString(R.string.enter_the_number)) {
                Toast.makeText(context,R.string.enter_the_number,Toast.LENGTH_SHORT).show()
            } else {
                onEqualClickListener?.onEqualClick(value.toLong())
                value=context.getString(R.string.enter_the_number)
                updateText()
            }
        }
    }

    private fun updateText() {
        answer.text = value
    }

    interface OnEqualMarkClickListener {

        fun onEqualClick(result: Long)
    }

}