package com.example.mysqlitedbproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mysqlitedbproject.databinding.ActivityEditNotesPageBinding
import com.google.gson.Gson

class EditNotesPage : AppCompatActivity() {
    private lateinit var dataBinding: ActivityEditNotesPageBinding
    private lateinit var factory: DBViewModelFactory
    private lateinit var viewModel: DBViewModel
    private lateinit var notes: NotesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_notes_page)
        factory = DBViewModelFactory(DBRepository(this))
        viewModel = ViewModelProvider(this, factory)[DBViewModel::class.java]


        val intentData = intent.getStringExtra(Keys.NOTES)
        notes = Gson().fromJson(intentData, NotesData::class.java)

        dataBinding.etEdTitle.setText(notes.Title)
        dataBinding.etEdDescrip.setText(notes.Descrip)

        dataBinding.btnUpdate.setOnClickListener {

//            if (dataBinding.etEdTitle.text.toString().isNotEmpty() && dataBinding.etEdDescrip.text.toString().isNotEmpty()){
                viewModel.updateRecord(
                    dataBinding.etEdTitle.text.toString(), dataBinding.etEdDescrip.text.toString(),
                    dataBinding.dcTime.text.toString(), notes.id)
                finish()
//            }else{
//                viewModel.deleteRecord(notes.id)
//                finish()
//            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_page_edit_option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ed_edit -> {
                dataBinding.etEdTitle.isFocusableInTouchMode = true
                dataBinding.etEdDescrip.isFocusableInTouchMode = true
                dataBinding.btnUpdate.visibility =View.VISIBLE
            }
            R.id.ed_delete -> {
                viewModel.deleteRecord(notes.id)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}