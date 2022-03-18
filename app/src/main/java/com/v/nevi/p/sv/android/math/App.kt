package com.v.nevi.p.sv.android.math

import android.app.Application
import com.v.nevi.p.sv.android.math.model.data.source.HistoryRepository
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings

class App:Application() {

    val repository:HistoryRepository
    get() = ServiceLocator.provideHistoryRepository(this)

    override fun onCreate() {
        super.onCreate()
        PlaySettings.initialize(SerializableManager.readSerializable<PlaySettings>(this,FileNameSerializedObjects.PLAY_SETTINGS))
    }
}