package com.v.nevi.p.sv.android.math.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.v.nevi.p.sv.android.math.App
import com.v.nevi.p.sv.android.math.R
import com.v.nevi.p.sv.android.math.model.HistoryPlay

fun Fragment.findTopNavController():NavController{
    val topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}

fun Fragment.setResult(result: Int,key:String){
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key,result)
}

fun Fragment.getResult(key: String)=findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(key)

fun Fragment.getFactoryWithRepository():HistoriesRepositoryViewModelFactory{
    val repository = (requireContext().applicationContext as App).repository
    return HistoriesRepositoryViewModelFactory(repository)
}

fun Fragment.getFactoryWithPlayHistory(historyPlay: HistoryPlay):HistoryPlayViewModelFactory {
    return HistoryPlayViewModelFactory(historyPlay)
}