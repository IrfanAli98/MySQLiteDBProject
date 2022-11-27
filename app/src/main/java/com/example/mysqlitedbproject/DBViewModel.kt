package com.example.mysqlitedbproject

import androidx.lifecycle.ViewModel

class DBViewModel(private val repository:DBRepository):ViewModel() {
    fun saveRecord(title:String, descrip:String, time:String){
        repository.saveRecords(title, descrip, time)
    }

    fun getNotesRecord():List<NotesData>{
        return repository.getRecords()
    }

    fun updateRecord(title:String, descrip:String, createdAt:String, srNo:Int){
        repository.updateRecord(title, descrip,createdAt, srNo)
    }

    fun deleteRecord(srNo:Int){
        repository.deleteSingleRecord(srNo)
    }

    fun deleteAllRecord(){
        repository.deleteAllRecord()
    }
}