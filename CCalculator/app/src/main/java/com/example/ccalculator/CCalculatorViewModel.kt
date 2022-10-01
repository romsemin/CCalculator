package com.example.ccalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope

class CCalculatorViewModel : ViewModel() {
    private val _listButtons: MutableLiveData<Array<Buttons>> = MutableLiveData()
    val listButtons: LiveData<Array<Buttons>> = _listButtons


}

//class MoreViewModel : ViewModel(), IMoreView {
//    private val _listButtons: MutableLiveData<Array<MoreType>> = MutableLiveData()
//    val listButtons: LiveData<Array<MoreType>> = _listButtons
//
//    private val _dialogWindowStatus: MutableLiveData<Boolean> = MutableLiveData()
//    val dialogWindowStatus: LiveData<Boolean> = _dialogWindowStatus
//
//    private val _errorLiveData: MutableLiveData<Int> = MutableLiveData()
//    val errorLiveData: LiveData<Int> = _errorLiveData
//
//    override var viewOutput: IMoreViewOutput? = null
//    override val coroutineScope: CoroutineScope = viewModelScope
//
//    fun init() {
//        viewOutput?.viewCreated()
//    }
//
//    override fun showMoreView(buttons: Array<MoreType>) {
//        _listButtons.postValue(buttons)
//    }
//
//    override fun showSignOutAlert() {
//        _dialogWindowStatus.postValue(true)
//    }
//
//    override fun showSignOutError(errorId: Int) {
//        _errorLiveData.postValue(errorId)
//    }
//
//    fun handleClickingSignOut() {
//        viewOutput?.signOutPressed()
//    }
//
//    fun handleClickingOn(type: MoreType) {
//        viewOutput?.optionSelected(type)
//    }
//
//    fun handleShowingSignOutDialog() {
//        _dialogWindowStatus.postValue(false)
//    }
//}