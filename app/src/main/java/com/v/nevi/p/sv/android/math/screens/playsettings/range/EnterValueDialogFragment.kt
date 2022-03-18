package com.v.nevi.p.sv.android.math.screens.playsettings.range

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.v.nevi.p.sv.android.math.databinding.DialogEnterValueBinding
import com.v.nevi.p.sv.android.math.screens.playsettings.viewpager.PagerFragment
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.setResult


class EnterValueDialogFragment : DialogFragment() {

    private lateinit var binding: DialogEnterValueBinding
    private val viewModel: EnterValueViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogEnterValueBinding.inflate(layoutInflater).apply {
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
            dismiss()
        })
        viewModel.onDoneEvent.observe(this, EventObserver {
            setResult(it,PagerFragment.KEY_NEW_VALUE)
            dismiss()
        })
    }

    companion object {
        const val KEY_CURRENT_VALUE = "current-value"
    }
}
