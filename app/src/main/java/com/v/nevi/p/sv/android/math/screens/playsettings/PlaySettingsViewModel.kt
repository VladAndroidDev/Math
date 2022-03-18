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

    val playSettings: PlaySettings = PlaySettings.getInstance()

    private val _onStartClickEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onStartClickEvent:LiveData<Event<Unit>> = _onStartClickEvent

    fun onStartClick(view: View){
        SerializableManager.saveSerializable(view.context,playSettings,FileNameSerializedObjects.PLAY_SETTINGS)
        _onStartClickEvent.value=Event(Unit)
    }


}