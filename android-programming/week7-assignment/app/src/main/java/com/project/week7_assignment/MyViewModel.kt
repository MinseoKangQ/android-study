package com.project.week7_assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModel(count : Int) : ViewModel() {

    val countLiveData = MutableLiveData<Int>()

    init {
        countLiveData.value = count // 전달받은 인자로 변수 초기화
    }

    fun increaseCount() {
        countLiveData.value = (countLiveData.value ?: 0) + 1
    }

    fun decreaseCount() {
        countLiveData.value = (countLiveData.value ?: 0) - 1
    }

}

// 초기값 받기 위함
class MyViewModelFactory(private val count: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MyViewModel(count) as T
    }
}