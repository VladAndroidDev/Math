package com.v.nevi.p.sv.android.math.screens.play

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.utils.setResult

class ExitDialogFragment:DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { frActivity ->
            val builder = MaterialAlertDialogBuilder(frActivity, R.style.AlertDialogThemeExit)
            builder.setMessage(R.string.do_you_really_want_exit)
                .setPositiveButton(R.string.yes_button) { dialog, _ ->
                    setResult(PlayFragment.KEY_EXIT_DIALOG_ARG,true)
                    dialog.cancel()
                }.setNegativeButton(R.string.no_button) { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalAccessException()
    }

}