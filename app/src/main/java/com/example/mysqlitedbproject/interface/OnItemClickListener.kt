package com.example.mysqlitedbproject.`interface`

import com.example.mysqlitedbproject.NotesData

interface OnItemClickListener {
    fun onItemClick(notes: NotesData, position:Int)
}