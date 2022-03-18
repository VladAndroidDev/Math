package com.v.nevi.p.sv.android.math.screens.playsettings.viewpager

import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.model.playsettings.OperationSettings
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings
import com.v.nevi.p.sv.android.math.utils.Event

class PagerViewModel(position: Int) : ViewModel() {

    private val _onEnterFirstValueClickEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val onEnterFirstValueClickEvent: LiveData<Event<Int>> = _onEnterFirstValueClickEvent

    private val _onEnterSecondValueClickEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val onEnterSecondValueClickEvent: LiveData<Event<Int>> = _onEnterSecondValueClickEvent

    private val _showToastEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val showToastEvent: LiveData<Event<Int>> = _showToastEvent

    private fun showToast(msgId: Int) {
        _showToastEvent.value = Event(msgId)
    }

    val operationSettings: OperationSettings =
        PlaySettings.getInstance().arrayOperationSettings[position]

    private var firstWasClicked: Boolean = false

    fun onEnterFirstValueClick() {
        firstWasClicked = true
        _onEnterFirstValueClickEvent.value = Event(operationSettings.valueStartRange)
    }

    fun onEnterSecondValueClick() {
        firstWasClicked = false
        _onEnterSecondValueClickEvent.value = Event(operationSettings.valueEndRange)
    }

    fun setResult(newValue: Int) {
        if (newValue > operationSettings.valueEndRange || newValue < operationSettings.valueStartRange) {
            showToast(R.string.msg_error_incorrect_value)
            return
        }
        if (firstWasClicked) {
            operationSettings.valueStartRange = newValue
        } else {
            operationSettings.valueEndRange = newValue
        }
    }

    fun onCheckedChange(button: CompoundButton, isChecked: Boolean) {
        button.text = if (isChecked) {
            button.context.getString(R.string.operation_enabled)
        } else {
            button.context.getString(R.string.operation_disabled)
        }
        operationSettings.checked = isChecked
    }
}