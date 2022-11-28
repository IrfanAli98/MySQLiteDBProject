package com.example.mysqlitedbproject

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysqlitedbproject.`interface`.OnItemClickListener
import com.example.mysqlitedbproject.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var factory: DBViewModelFactory
    private lateinit var viewModel:DBViewModel
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        dataBinding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        factory = DBViewModelFactory(DBRepository(this))
        viewModel= ViewModelProvider(this, factory)[DBViewModel::class.java]


        val layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dataBinding.rcwView.layoutManager = layoutManager
        dataBinding.rcwView.setHasFixedSize(true)

        updateUI()

        dataBinding.ftBtnAdd.setOnClickListener {
            val dialog = Dialog(this)
            val manager= WindowManager.LayoutParams()
            manager.width = WindowManager.LayoutParams.MATCH_PARENT
            manager.height = WindowManager.LayoutParams.WRAP_CONTENT

            dialog.setContentView(R.layout.create_note_dialogue)
            dialog.setCancelable(false)

            dialog.window!!.attributes=manager

            val btnClose= dialog.findViewById<ImageButton>(R.id.btn_close)
            val etTitle = dialog.findViewById<EditText>(R.id.et_title)
            val etDescrip= dialog.findViewById<EditText>(R.id.et_Descrip)
            val clkTime= dialog.findViewById<TextView>(R.id.clk_time)
            val btnSave= dialog.findViewById<Button>(R.id.btn_save)

            btnSave.setOnClickListener {
                if(etTitle.text.toString().isNotEmpty()&&etDescrip.text.toString().isNotEmpty()){
                viewModel.saveRecord(etTitle.text.toString(), etDescrip.text.toString(), clkTime.text.toString())
                    updateUI()
                    dialog.dismiss()
                }

                else Toast.makeText(this, "Enter the Title and Description", Toast.LENGTH_SHORT).show()

            }
            btnClose.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option_items, menu)
        return super.onCreateOptionsMenu(menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.it_delete->{
                viewModel.deleteAllRecord()
                updateUI()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun getNotesList(): List<NotesData>{
        return viewModel.getNotesRecord()
    }
    fun updateUI(){
        adapter=MyRecyclerViewAdapter(getNotesList(), object : OnItemClickListener{
            override fun onItemClick(notes: NotesData, position: Int) {
                val intent = Intent(this@MainActivity, EditNotesPage::class.java)
                intent.putExtra(Keys.NOTES, Gson().toJson(notes))
                startActivity(intent)
            }
        })
        dataBinding.rcwView.adapter=adapter
    }

    override fun onRestart() {
        super.onRestart()
        updateUI()
    }
}