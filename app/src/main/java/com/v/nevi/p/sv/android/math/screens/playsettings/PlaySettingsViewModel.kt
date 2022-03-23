package com.v.nevi.p.sv.android.math.screens.playsettings

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings
import com.v.nevi.p.sv.android.math.preferences.MathPreferences
import com.v.nevi.p.sv.android.math.utils.Event


class PlaySettingsViewModel : ViewModel() {

    val playSettings: PlaySettings = PlaySettings.getInstance()

    private val _onStartClickEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onStartClickEvent:LiveData<Event<Unit>> = _onStartClickEvent

    private val _showToastEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val showToastEvent: LiveData<Event<Int>> = _showToastEvent

    private fun showToast(msgId: Int) {
        _showToastEvent.value = Event(msgId)
    }

    fun onStartClick(view: View) {
        if (playSettings.getEnabledOperations().isEmpty()) {
            showToast(R.string.toast_msg_must_be_enabled)
        } else {
            MathPreferences.savePlaySettings(view.context,playSettings)
            _onStartClickEvent.value = Event(Unit)
        }
    }
}