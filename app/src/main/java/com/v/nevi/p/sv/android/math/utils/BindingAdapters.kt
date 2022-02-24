package com.v.nevi.p.sv.android.math.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.screens.playsettings.PlaySettingsViewModel
import com.v.nevi.p.sv.android.math.views.CalculatorView

@BindingAdapter(value = ["onEqualClick"])
fun setOnEqualClickListener(
    calculatorView: CalculatorView,
    listener:CalculatorView.OnEqualMarkClickListener
){
    calculatorView.onEqualClickListener = listener
}

@BindingAdapter(value = ["textViewLabel", "viewModel","startValue"], requireAll = true)
fun onProgressChangedListener(
    slider: Slider,
    label: TextView,
    viewModel: PlaySettingsViewModel,
    startValue: Int
) {
    slider.addOnChangeListener { _slider: Slider, value: Float, _: Boolean ->
        val context = label.context
        val valueInt = value.toInt()
        when (slider.id) {
            R.id.game_duration_seek_bar -> {
                label.text = if (valueInt == 0) {
                    context.getString(R.string.not_limited)
                } else {
                    String.format("$valueInt ${context.getString(R.string.time)}")
                }
                viewModel.playSettings.gameDurationMin = valueInt
            }
            R.id.number_tasks_seek_bar -> {
                label.text = if (valueInt == 0) {
                    context.getString(R.string.not_limited)
                } else {
                    String.format("$valueInt ${context.getString(R.string.tasks)}")
                }
                viewModel.playSettings.numberTasks = valueInt
            }
            R.id.number_answers_seek_bar -> {
                label.text = if (valueInt == 0) {
                    context.getString(R.string.manual_input)
                } else {
                    String.format("$valueInt ${context.getString(R.string.variants)}")
                }
                viewModel.playSettings.numberAnswers = valueInt
            }
        }
    }
    slider.value = startValue.toFloat()
}