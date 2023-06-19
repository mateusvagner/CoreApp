package com.pw3.coreapp.ui.financial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FinancialViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Financial Fragment"
    }
    val text: LiveData<String> = _text
}