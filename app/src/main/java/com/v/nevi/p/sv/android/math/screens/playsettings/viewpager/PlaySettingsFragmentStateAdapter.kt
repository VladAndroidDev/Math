package com.v.nevi.p.sv.android.math.screens.playsettings.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.v.nevi.p.sv.android.math.model.playsettings.PlaySettings

class PlaySettingsFragmentStateAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = PlaySettings.NUMBER_OPERATIONS

    override fun createFragment(position: Int) = PagerFragment.NewInstance(position)

}