package com.v.nevi.p.sv.android.math.utils

import android.content.Context
import android.os.SystemClock
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.model.StatisticsItem
import com.v.nevi.p.sv.android.math.model.data.History
import com.v.nevi.p.sv.android.math.screens.play.ChronometerSettings
import com.v.nevi.p.sv.android.math.screens.play.PlayViewModel
import com.v.nevi.p.sv.android.math.screens.play.TimeListener
import com.v.nevi.p.sv.android.math.screens.play.adapters.AnswerOptionsAdapter
import com.v.nevi.p.sv.android.math.screens.playsettings.PlaySettingsViewModel
import com.v.nevi.p.sv.android.math.screens.statistics.StatisticsAdapter
import com.v.nevi.p.sv.android.math.screens.tabs.history.HistoryAdapter
import com.v.nevi.p.sv.android.math.views.CalculatorView
import com.v.nevi.p.sv.android.math.views.RecyclerViewHistory

@BindingAdapter(value = ["change_listener"])
fun setTimeListener(chronometer: Chronometer, listener: TimeListener) {
    chronometer.setOnChronometerTickListener {
        val timeValues = chronometer.text.toString().split(':')
        val minutes = timeValues[0].toLong()
        val seconds = timeValues[1].toLong()
        val secondsAll: Long = if (minutes > 0) {
            minutes * seconds
        } else {
            seconds
        }
        listener.checkTime(secondsAll)
    }
}

@BindingAdapter(value = ["is_work"])
fun setIsWork(chronometer: Chronometer, settings: ChronometerSettings?) {
    if (settings == null) return
    settings.time?.let {
        if (it == 0L) return@let
        setBase(chronometer, it)
    }
    if (settings.isWork) {
        chronometer.start()
    } else {
        chronometer.stop()
    }
}

fun setBase(chronometer: Chronometer, seconds: Long) {
    chronometer.base = SystemClock.elapsedRealtime() - seconds * 1000
}

@BindingAdapter(value = ["value"])
fun insertValue(recyclerView: RecyclerViewHistory, item: StatisticsItem.ItemPlayHistory?) {
    if (item != null) {
        recyclerView.insert(item)
    }
}

@BindingAdapter(value = ["history_items"])
fun setHistoryItems(recyclerView: RecyclerView, listItems: List<History>?) {
    listItems?.let {
        recyclerView.adapter = HistoryAdapter(it.reversed())
    }
}
//@BindingAdapter(value = ["viewModel"])
//fun setViewModel(recyclerView: RecyclerView,viewModel: PlayViewModel){
//    recyclerView.adapter = AnswerOptionsAdapter(viewModel)
//}

@BindingAdapter(value = ["items"], requireAll = true)
fun setItems(recyclerView: RecyclerView, listItems: List<Int>?) {
    listItems?.let {
        val adapter = recyclerView.adapter as AnswerOptionsAdapter
        adapter.listItems = it
    }
}

@BindingAdapter(value = ["itemsStatistics"])
fun setItemsStatistics(recyclerView: RecyclerView, listItems: List<StatisticsItem>) {
    recyclerView.adapter = StatisticsAdapter(listItems)
}

@BindingAdapter(value = ["onEqualClick"])
fun setOnEqualClickListener(
    calculatorView: CalculatorView,
    listener: CalculatorView.OnEqualMarkClickListener
) {
    calculatorView.onEqualClickListener = listener
}

@BindingAdapter(value = ["numberForSpanCount"])
fun setNumberAnswers(recyclerView: RecyclerView, numberAnswers: Int) {
    if (numberAnswers == 0) return
    val spanCount = when {
        numberAnswers == 4 -> {
            2
        }
        numberAnswers % 3 == 0 -> {
            3
        }
        else -> {
            2
        }
    }
    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, spanCount)
}

@BindingAdapter(value = ["textViewLabel", "viewModel", "startValue"], requireAll = true)
fun onProgressChangedListener(
    slider: Slider,
    label: TextView,
    viewModel: PlaySettingsViewModel,
    value: Int
) {
    val context = label.context
    when (slider.id) {
        R.id.game_duration_seek_bar -> {
            setTextGameDurationTextViewLabel(value, label, context)
            slider.value = (value / 30).toFloat()
            slider.setLabelFormatter {
                label.text.toString()
            }
        }
        R.id.number_tasks_seek_bar -> {
            setTextNumberTasksTextViewLabel(value, label, context)
            slider.value = value.toFloat()
        }
        R.id.number_answers_seek_bar -> {
            setTextNumberAnswersTextViewLabel(value, label, context)
            slider.value = numberAnswersFromShowsToValues(value).toFloat()
            slider.setLabelFormatter {
                val valueShows = numberAnswersFromValuesToShows(it.toInt())
                val text = if (valueShows == 0) {
                    context.getString(R.string.manual_input)
                } else {
                    "$valueShows"
                }
                text
            }
        }
    }
    slider.isLongClickable=false
    slider.addOnChangeListener { _: Slider, _value: Float, _: Boolean ->
        val valueInt = _value.toInt()
        when (slider.id) {
            R.id.game_duration_seek_bar -> {
                val timeInSeconds = valueInt * 30
                setTextGameDurationTextViewLabel(timeInSeconds, label, context)
                viewModel.playSettings.gameDuration = valueInt * 30
            }
            R.id.number_tasks_seek_bar -> {
                setTextNumberTasksTextViewLabel(valueInt, label, context)
                viewModel.playSettings.numberTasks = valueInt
            }
            R.id.number_answers_seek_bar -> {
                val valueShows = numberAnswersFromValuesToShows(valueInt)
                setTextNumberAnswersTextViewLabel(valueShows, label, context)
                viewModel.playSettings.numberAnswers = valueShows
            }
        }
    }
}

fun setTextGameDurationTextViewLabel(timeInSeconds: Int, label: TextView, context: Context):String {
    val time = if (timeInSeconds == 0) {
        context.getString(R.string.not_limited)
    } else {
        val minutes = timeInSeconds / 60
        val seconds = timeInSeconds - (minutes * 60)
        val minutesInString = createStringRepresentationTimeValue(minutes)
        val secondsInString = createStringRepresentationTimeValue(seconds)
        String.format("$minutesInString:$secondsInString")
    }
    label.text = time
    return time
}

fun createStringRepresentationTimeValue(timeValue: Int): String {
    return if (timeValue < 10) {
        "0$timeValue"
    } else {
        timeValue.toString()
    }
}

fun setTextNumberTasksTextViewLabel(value: Int, label: TextView, context: Context) {
    label.text = if (value == 0) {
        context.getString(R.string.not_limited)
    } else {
        String.format("$value ${context.getString(R.string.tasks)}")
    }
}

fun setTextNumberAnswersTextViewLabel(value: Int, label: TextView, context: Context) {
    label.text = if (value == 0) {
        context.getString(R.string.manual_input)
    } else {
        String.format("$value ${context.getString(R.string.variants)}")
    }
}

fun numberAnswersFromValuesToShows(value: Int): Int {
    return when (value) {
        1 -> 4
        2 -> 6
        3 -> 9
        4 -> 12
        else -> 0
    }
}

fun numberAnswersFromShowsToValues(value: Int): Int {
    return when (value) {
        4 -> 1
        6 -> 2
        9 -> 3
        12 -> 4
        else -> 0
    }
}