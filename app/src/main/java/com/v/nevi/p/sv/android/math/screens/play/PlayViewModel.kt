package com.v.nevi.p.sv.android.math.screens.play

import android.util.Log
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.math.views.CalculatorView

class PlayViewModel:ViewModel(),CalculatorView.OnEqualMarkClickListener {

    override fun onEqualClick(result:Long) {
        Log.d("MyTag",result.toString())
    }

}