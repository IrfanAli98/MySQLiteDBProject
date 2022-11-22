package com.example.mysqlitedbproject

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mysqlitedbproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var factory: DBViewModelFactory
    private lateinit var viewModel:DBViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        dataBinding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        factory = DBViewModelFactory(DBRepository(this))
        viewModel= ViewModelProvider(this, factory)[DBViewModel::class.java]

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

            }

        }
        return super.onOptionsItemSelected(item)
    }
}