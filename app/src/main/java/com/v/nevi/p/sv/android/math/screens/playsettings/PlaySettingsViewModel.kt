package com.v.nevi.p.sv.android.math.screens.playsettings

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.FileNameSerializedObjects
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.SerializableManager
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings
import com.v.nevi.p.sv.android.math.utils.Event


class PlaySettingsViewModel : ViewModel() {

    private val _onEnterFirstValueClickEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val onEnterFirstValueClickEvent: LiveData<Event<Int>> = _onEnterFirstValueClickEvent

    private val _onEnterSecondValueClickEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val onEnterSecondValueClickEvent: LiveData<Event<Int>> = _onEnterSecondValueClickEvent

    private val _showToastEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val showToastEvent: LiveData<Event<Int>> = _showToastEvent

    private var firstWasClicked: Boolean = false

    val playSettings: PlaySettings = PlaySettings.getInstance()

    private fun showToast(msgId: Int) {
        _showToastEvent.value = Event(msgId)
    }



    fun onEnterFirstValueClick() {
        firstWasClicked = true
        _onEnterFirstValueClickEvent.value = Event(playSettings.valueStartRange)
    }

    fun onEnterSecondValueClick() {
        firstWasClicked = false
        _onEnterSecondValueClickEvent.value = Event(playSettings.valueEndRange)
    }

    private val _onStartClickEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onStartClickEvent:LiveData<Event<Unit>> = _onStartClickEvent

    fun onStartClick(view: View){
        SerializableManager.saveSerializable(view.context,playSettings,FileNameSerializedObjects.PLAY_SETTINGS)
        _onStartClickEvent.value=Event(Unit)
    }

    fun setResult(newValue: Int) {
        if (newValue > playSettings.valueEndRange || newValue < playSettings.valueStartRange) {
            showToast(R.string.msg_error_incorrect_value)
            return
        }
        if (firstWasClicked) {
            playSettings.valueStartRange = newValue
        } else {
            playSettings.valueEndRange = newValue
        }
    }
}