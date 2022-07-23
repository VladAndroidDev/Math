package com.v.nevi.p.sv.android.math.preferences

import android.content.Context
import android.util.JsonReader
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings
import java.lang.IllegalStateException


private const val KEY_PLAY_SETTINGS="key-play-settings"
object MathPreferences {

    fun  savePlaySettings(context: Context,playSettings: PlaySettings) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        val gson = Gson()
        val jsonObject = gson.toJson(playSettings)
        editor.putString(KEY_PLAY_SETTINGS,jsonObject)
        editor.apply()
    }

    fun getPlaySettings(context: Context): PlaySettings? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonObject = preferences.getString(KEY_PLAY_SETTINGS, null)
        val gson = Gson()
        var playSettings:PlaySettings? = null
        try {
            playSettings = gson.fromJson(jsonObject, PlaySettings::class.java)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return playSettings
    }

}