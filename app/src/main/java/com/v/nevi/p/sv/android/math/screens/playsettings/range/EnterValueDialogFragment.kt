package com.v.nevi.p.sv.android.math.screens.playsettings.range

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.databinding.FragmentEnterValueDialogBinding
import com.v.nevi.p.sv.android.math.screens.playsettings.PlaySettingsFragment
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.setResult


class EnterValueDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentEnterValueDialogBinding
    private val viewModel: EnterValueViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val layoutInflater = requireActivity().layoutInflater
        binding = FragmentEnterValueDialogBinding.inflate(layoutInflater).apply {
            viewModel.value = arguments?.getInt(KEY_CURRENT_VALUE).toString()
            this.viewmodel = viewModel
        }
        binding.lifecycleOwner = this
        setObservers()
        val builder = MaterialAlertDialogBuilder(requireContext())
        return builder.setView(binding.root).create()
    }

    private fun setObservers() {
        viewModel.onCancelEvent.observe(this, EventObserver {
            dialog?.dismiss()
        })
        viewModel.onDoneEvent.observe(this, EventObserver {
            setResult(it,PlaySettingsFragment.KEY_NEW_VALUE)
            dismiss()
        })
    }

    companion object {
        const val KEY_CURRENT_VALUE = "current-value"
    }
}
