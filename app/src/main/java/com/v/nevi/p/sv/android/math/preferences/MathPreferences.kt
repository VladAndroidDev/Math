package com.v.nevi.p.sv.android.math.preferences

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.v.nevi.p.sv.android.math.model.operations.AdditionOperation
import com.v.nevi.p.sv.android.math.model.operations.DivisionOperation
import com.v.nevi.p.sv.android.math.model.operations.MultiplicationOperation
import com.v.nevi.p.sv.android.math.model.operations.SubtractionOperation
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings


private const val KEY_PLAY_SETTINGS="key-play-settings"
object MathPreferences {

    fun  savePlaySettings(context: Context,playSettings: PlaySettings) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create()
        val jsonObject = gson.toJson(playSettings)
        editor.putString(KEY_PLAY_SETTINGS,jsonObject)
        editor.apply()
    }

    fun getPlaySettings(context: Context): PlaySettings? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonObject = preferences.getString(KEY_PLAY_SETTINGS, null)
        var playSettings:PlaySettings?=null
        try {
            val gson = Gson()
            playSettings = gson.fromJson(jsonObject, PlaySettings::class.java)
            playSettings!!.operationsSettings[0].operationClass = AdditionOperation::class.java
            playSettings.operationsSettings[1].operationClass = SubtractionOperation::class.java
            playSettings.operationsSettings[2].operationClass = MultiplicationOperation::class.java
            playSettings.operationsSettings[3].operationClass = DivisionOperation::class.java
        }catch (e:Exception){

        }
        return playSettings
    }

}