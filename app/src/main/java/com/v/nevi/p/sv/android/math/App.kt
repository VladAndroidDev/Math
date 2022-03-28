package com.v.nevi.p.sv.android.math

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.v.nevi.p.sv.android.math.model.data.source.HistoryRepository
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings
import com.v.nevi.p.sv.android.math.preferences.MathPreferences

class App:Application() {

    val repository:HistoryRepository
    get() = ServiceLocator.provideHistoryRepository(this)

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        PlaySettings.initialize(MathPreferences.getPlaySettings(this))
    }
}