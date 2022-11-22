package com.example.mysqlitedbproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DBViewModelFactory(private val repository:DBRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DBViewModel::class.java)){
            return DBViewModel(repository)as T
        }
        throw IllegalArgumentException("Unknown")
    }

}