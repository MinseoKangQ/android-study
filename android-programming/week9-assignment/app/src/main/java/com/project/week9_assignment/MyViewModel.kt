package com.project.week9_assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var liveData = MutableLiveData<Int>()
    init { liveData.value = 0 }
    fun increase() { liveData.value = (liveData.value ?: 0) + 1 }
}