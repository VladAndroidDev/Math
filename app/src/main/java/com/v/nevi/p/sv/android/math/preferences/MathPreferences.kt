package com.v.nevi.p.sv.android.math.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings

private const val PLAY_SETTINGS_PREFERENCES="play-settings"
private const val KEY_PLAY_SETTINGS="key-play-settings"
object MathPreferences {

    fun savePlaySettings(context: Context, playSettings: PlaySettings){
        val settings = context.getSharedPreferences(PLAY_SETTINGS_PREFERENCES,MODE_PRIVATE)

    }
}