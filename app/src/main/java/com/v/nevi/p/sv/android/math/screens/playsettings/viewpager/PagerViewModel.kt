package com.v.nevi.p.sv.android.math.screens.playsettings.viewpager

import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.model.playsettings.OperationSettings
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings
import com.v.nevi.p.sv.android.math.utils.Event

class PagerViewModel(private val position: Int) : ViewModel() {

    val operationSettings:OperationSettings = PlaySettings.getInstance().operationsSettings[position]

    private val _onEnterValueClickEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val onEnterValueClickEvent: LiveData<Event<Int>> = _onEnterValueClickEvent

    private val _showToastEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val showToastEvent: LiveData<Event<Int>> = _showToastEvent

    private fun showToast(msgId: Int) {
        _showToastEvent.value = Event(msgId)
    }

    private var firstWasClicked: Boolean = false

    fun onEnterFirstValueClick() {
        firstWasClicked = true
        _onEnterValueClickEvent.value = Event(position)
    }

    fun onEnterSecondValueClick() {
        firstWasClicked = false
        _onEnterValueClickEvent.value = Event(position)
    }

    fun setResult(newValue: Int) {
        if (!firstWasClicked && newValue <= operationSettings.valueStartRange) {
            showToast(R.string.msg_error_incorrect_value)
            return
        }
        if (firstWasClicked && newValue >= operationSettings.valueEndRange) {
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