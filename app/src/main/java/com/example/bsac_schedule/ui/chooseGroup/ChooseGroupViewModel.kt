package com.example.bsac_schedule.ui.chooseGroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChooseGroupViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Выберете группу"
    }
    val text: LiveData<String> = _text
}