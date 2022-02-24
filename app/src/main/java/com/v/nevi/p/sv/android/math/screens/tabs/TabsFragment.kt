package com.v.nevi.p.sv.android.math.screens.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentTabsBinding


class TabsFragment : Fragment() {

    private lateinit var binding: FragmentTabsBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.tabs_container) as NavHostFragment

        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNav,navController)
    }
}