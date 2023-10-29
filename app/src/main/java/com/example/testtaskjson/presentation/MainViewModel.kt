package com.example.testtaskjson.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testtaskjson.data.Receipt
import com.example.testtaskjson.data.remote.ReceiptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ReceiptRepository()
    private val _receiptList = MutableLiveData<List<Receipt>>()
    val receiptList : LiveData<List<Receipt>>
        get() = _receiptList
    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean>
        get() = _isDataLoading

    private suspend fun getRemoteData() {
        _isDataLoading.value = true
       val remoteFetchingJob = viewModelScope.launch(Dispatchers.IO) {
            repository.getAllReceipt(getApplication())
           _receiptList.postValue(repository.getAllReceipt(getApplication()))
        }
        remoteFetchingJob.join()
        _isDataLoading.value = false
    }

    init {
        viewModelScope.launch {
            getRemoteData()
        }
    }

}