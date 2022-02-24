package com.v.nevi.p.sv.android.math.utils

import android.hardware.camera2.TotalCaptureResult
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.v.nevi.p.sv.android.math.R
import java.lang.StringBuilder

fun Fragment.findTopNavController():NavController{
    val topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}

fun Fragment.setResult(result: Int,key:String){
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key,result)
}
fun Fragment.getResult(key: String)=findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(key)