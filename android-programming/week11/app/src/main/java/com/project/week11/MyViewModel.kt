package com.project.week11

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Item(val firstName : String, val lastName : String)

class MyViewModel : ViewModel() {

    val clickItem = MutableLiveData<Int>() // 클릭 기록
    val items = ArrayList<Item>()

    fun addItem(item : Item) {
        items.add(item)
    }

    fun deleteItem(pos : Int) {
        items.removeAt(pos)
    }
}