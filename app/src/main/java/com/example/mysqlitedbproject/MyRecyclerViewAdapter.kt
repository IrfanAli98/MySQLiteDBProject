package com.example.mysqlitedbproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mysqlitedbproject.databinding.LayoutCustomViewBinding

class MyRecyclerViewAdapter(private var notesList: List<NotesData>) :androidx.recyclerview.widget.RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder>() {

    class CustomViewHolder(layoutCustomViewBinding: LayoutCustomViewBinding): RecyclerView.ViewHolder(layoutCustomViewBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_custom_view,parent,false)
        return CustomViewHolder(LayoutCustomViewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val notes = notesList[position]

    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}