package com.v.nevi.p.sv.android.math.screens.playsettings.range

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.utils.Event

class EnterValueViewModel : ViewModel() {

    private val _onCancelEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val onCancelEvent: LiveData<Event<Unit>> = _onCancelEvent

    private val _onDoneEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val onDoneEvent: LiveData<Event<Int>> = _onDoneEvent

     var value=""

    fun onCancelClick() {
        _onCancelEvent.value = Event(Unit)
    }

    fun onDoneClick() {
        if(value==""){
            value="0"
        }else {
            if (value.length > 4) {
                value = if (value[0] == '-'){
                    value.substring(0,5)
                }else{
                    value.substring(0,4)
                }
            }
        }
        _onDoneEvent.value = Event(value.toInt())
    }
}