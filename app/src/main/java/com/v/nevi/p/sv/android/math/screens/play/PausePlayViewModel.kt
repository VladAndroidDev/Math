package com.v.nevi.p.sv.android.math.screens.play


import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.model.data.source.HistoryRepository
import com.v.nevi.p.sv.android.math.utils.Event

class PausePlayViewModel():ViewModel() {

    private val _onItemMenuClickEvent:MutableLiveData<Event<EventMenu>> = MutableLiveData()
    val onItemMenuClickEvent:LiveData<Event<EventMenu>> = _onItemMenuClickEvent

    fun onItemMenuClickEvent(view:View){
        _onItemMenuClickEvent.value = Event(view.tag as EventMenu)
    }
}