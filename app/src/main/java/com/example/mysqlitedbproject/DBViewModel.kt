package com.example.mysqlitedbproject

import androidx.lifecycle.ViewModel

class DBViewModel(private val repository:DBRepository):ViewModel() {
    fun saveRecord(title:String, descrip:String, time:String){
        repository.saveRecords(title, descrip, time)
    }
}