package com.v.nevi.p.sv.android.math.screens.play

import android.os.Parcelable
import android.view.View
import android.widget.Button
import androidx.lifecycle.*
import com.v.nevi.p.sv.android.math.model.Scene
import com.v.nevi.p.sv.android.math.model.StatisticsItem
import com.v.nevi.p.sv.android.math.model.Task
import com.v.nevi.p.sv.android.math.model.data.Result
import com.v.nevi.p.sv.android.math.model.data.source.HistoryRepository
import com.v.nevi.p.sv.android.math.utils.Event
import com.v.nevi.p.sv.android.math.utils.getCurrentDate
import com.v.nevi.p.sv.android.math.views.CalculatorView
import kotlinx.coroutines.launch

interface TimeListener{

    fun checkTime(time: Long)
}


class PlayViewModel(private val repository: HistoryRepository) : ViewModel(),CalculatorView.OnEqualMarkClickListener,TimeListener {

    private val scene = Scene()

    private val _onFinishPlayEvent: MutableLiveData<Event<Parcelable>> = MutableLiveData()
    val onFinishPlayEvent: LiveData<Event<Parcelable>> = _onFinishPlayEvent

    fun finishPlay(){
        viewModelScope.launch{
            if(!scene.isEmptyHistoryPlay())
                repository.getHistoryByDate(getCurrentDate()).let {
                    val historyPlay = scene.getHistoryPlay()
                    if(it is Result.Success){
                        historyPlay.update(it.data)
                        repository.updateHistory(historyPlay)
                    }else{
                        repository.saveHistory(historyPlay)
                    }
                }
            _onFinishPlayEvent.value = Event(scene.historyPlay)
        }
    }

    fun saveResult(){

    }
    private val _newTaskLiveData:MutableLiveData<Task> = MutableLiveData()

    private val _stringRepresentationTaskLiveData: LiveData<String> = _newTaskLiveData.map{
        it.toString()
    }

    val stringRepresentationTaskLiveData:LiveData<String> = _stringRepresentationTaskLiveData

    val numberAnswers
    get() = scene.numberAnswers

    private val _itemPlayHistory:MutableLiveData<StatisticsItem.ItemPlayHistory> = MutableLiveData()
    val itemPlayHistory = _itemPlayHistory

    override fun onEqualClick(result:Long) {
        checkAnswer(result)
    }

    fun onAnswerSelected(v: View) {
        if (v is Button) {
            val answer = v.text.toString().toLong()
            checkAnswer(answer)
        }
    }

    private fun checkAnswer(answer: Long){
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


    private fun continuePlay(){
        setTimerWork(true)
    }

    private val _onPausePlayEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onPausePlayEvent:LiveData<Event<Unit>> = _onPausePlayEvent

    fun pausePlay(){
        setTimerWork(false)
        _onPausePlayEvent.value = Event(Unit)
    }

    private val _timeIsWork:MutableLiveData<Boolean> = MutableLiveData()
    val timeIsWork:MutableLiveData<Boolean> = _timeIsWork

    private fun setTimerWork(b:Boolean){
        _timeIsWork.value = b
    }

    override fun checkTime(time: Long) {
        if(time == 0L) return
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

    fun setResult(eventMenu:Int) {
        when(eventMenu){
            EventMenu.CONTINUE.ordinal-> continuePlay()
            EventMenu.TOTALS.ordinal->finishPlay()
            EventMenu.EXIT.ordinal->exitPlay()
        }
    }

    private val _onExitEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onExitEvent:LiveData<Event<Unit>> = _onExitEvent

    private fun exitPlay() {
        finishPlay()
        _onExitEvent.value = Event(Unit)
    }
}