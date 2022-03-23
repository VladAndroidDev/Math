package com.v.nevi.p.sv.android.math.screens.tabs.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.utils.Event

class StartViewModel: ViewModel(){

    private val _startPlaySettingsEvent = MutableLiveData<Event<Unit>>()
    val startPlaySettingsEvent:LiveData<Event<Unit>> = _startPlaySettingsEvent

    fun startPlaySettings(){
        _startPlaySettingsEvent.value = Event(Unit)
    }

    private val _openSettingsEvent = MutableLiveData<Event<Unit>>()
    val openSettingsEvent:LiveData<Event<Unit>> = _openSettingsEvent

    fun openSettings(){
        _openSettingsEvent.value = Event(Unit)
    }

}