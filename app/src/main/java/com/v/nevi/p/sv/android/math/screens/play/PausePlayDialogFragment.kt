package com.v.nevi.p.sv.android.math.screens.play

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.v.nevi.p.sv.android.math.databinding.DialogPauseGameBinding
import com.v.nevi.p.sv.android.math.utils.EventObserver
import com.v.nevi.p.sv.android.math.utils.getFactoryWithRepository
import com.v.nevi.p.sv.android.math.utils.setResult

class PausePlayDialogFragment:DialogFragment() {

    private val viewModel:PausePlayViewModel by viewModels()

    private lateinit var binding: DialogPauseGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogPauseGameBinding.inflate(layoutInflater).apply {
            viewmodel = viewModel
        }
        binding.lifecycleOwner = this
        setObservers()
        val  builder = MaterialAlertDialogBuilder(requireContext())
        return builder.setView(binding.root).create()
    }

    private fun setObservers(){
        viewModel.onItemMenuClickEvent.observe(this,EventObserver{
            setResult(it.ordinal, PlayFragment.KEY_EVENT_MENU)
            dismiss()
        })
    }

}