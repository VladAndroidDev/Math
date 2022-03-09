package com.v.nevi.p.sv.android.math.screens.play

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.v.nevi.p.sv.android.math.databinding.ManualInputViewBinding
import com.v.nevi.p.sv.android.math.databinding.RecyclerViewOptionsAnswerBinding
import com.v.nevi.p.sv.android.math.model.Scene
import com.v.nevi.p.sv.android.math.model.StatisticsItem
import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.screens.play.adapters.AnswerOptionsAdapter
import com.v.nevi.p.sv.android.math.utils.Event
import com.v.nevi.p.sv.android.math.views.CalculatorView

interface TimeListener{

    fun checkTime(time: Int)
}


class PlayViewModel:ViewModel(),CalculatorView.OnEqualMarkClickListener,TimeListener {


    private val scene = Scene()

    private val _newTaskLiveData:MutableLiveData<Task> = MutableLiveData()

    private val _stringRepresentationTaskLiveData: LiveData<String> = _newTaskLiveData.map{
        it.toString()
    }

    val stringRepresentationTaskLiveData:LiveData<String> = _stringRepresentationTaskLiveData

    val numberAnswers
    get() = scene.numberAnswers

    private val _itemPlayHistory:MutableLiveData<StatisticsItem.ItemPlayHistory> = MutableLiveData()
    val itemPlayHistory = _itemPlayHistory

    override fun onEqualClick(answer:Long) {
        createItemHistory(answer)
        updateNumbersAnswers(scene.numberCorrectAnswers,scene.numberInCorrectAnswers)
        if(scene.isTasksOver()){
            finishPlay()
        }
        getTask()
        getAnswers()
    }

    private val _itemsOptionsList:MutableLiveData<List<Long>> = MutableLiveData()
    val itemsOptionsList:LiveData<List<Long>> = _itemsOptionsList

    private fun getAnswers() {
        if (numberAnswers > 0) {
            _itemsOptionsList.value = scene.getAnswers()
        }
    }

    private fun createItemHistory(answer: Long){
        _itemPlayHistory.value = scene.createItemPlayHistory(answer)
    }

    private fun getTask(){
        _newTaskLiveData.value = scene.getTask()
    }

    fun startPlay(){
        getTask()
        getAnswers()
        updateNumbersAnswers(scene.numberCorrectAnswers,scene.numberInCorrectAnswers)
        setTimerWork(true)
    }

    private val _timeIsWork:MutableLiveData<Boolean> = MutableLiveData()
    val timeIsWork:MutableLiveData<Boolean> = _timeIsWork

    private fun setTimerWork(b:Boolean){
        _timeIsWork.value = b
    }

    fun createBinding(inflater: LayoutInflater,parent:ViewGroup?):ViewDataBinding{
        return if(scene.numberAnswers==0){
            ManualInputViewBinding.inflate(inflater,parent,false).apply {
                viewmodel = this@PlayViewModel
            }
        }else{
            RecyclerViewOptionsAnswerBinding.inflate(inflater,parent,false).apply {
                viewmodel = this@PlayViewModel
                recyclerViewOptions.adapter = AnswerOptionsAdapter(this@PlayViewModel)
            }
        }
    }

    private val _onFinishGameEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onFinishGameEvent:LiveData<Event<Unit>> = _onFinishGameEvent

    private fun finishPlay(){
        _onFinishGameEvent.value = Event(Unit)
    }

    override fun checkTime(time: Int) {
        if(time == 0) return
        scene.updateTime(time)
        if(scene.isTimeOver()){
            finishPlay()
        }
    }

    private val _numberCorrectAnswers:MutableLiveData<Int> = MutableLiveData()
    val numberCorrectAnswers:LiveData<Int> = _numberCorrectAnswers

    private val _numberInCorrectAnswers:MutableLiveData<Int> = MutableLiveData()
    val numberInCorrectAnswers:LiveData<Int> = _numberInCorrectAnswers

    private fun updateNumbersAnswers(numberCorrectAnswers:Int, numberInCorrectAnswers:Int){
        _numberCorrectAnswers.value = numberCorrectAnswers
        _numberInCorrectAnswers.value = numberInCorrectAnswers
    }
}