package com.example.sqllitedemokt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.sqllitedemokt.Adapter.ListViewAdapter
import com.example.sqllitedemokt.Model.Person

class MainActivity : AppCompatActivity() {
    lateinit var editId: EditText
    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var btnAdd: Button
    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button
    lateinit var listPersons: ListView
    internal lateinit var dbHelper: DBHelper
    internal var firstPerson: List<Person> = ArrayList<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd = findViewById(R.id.btn_add)
        btnUpdate = findViewById(R.id.btn_update)
        btnDelete = findViewById(R.id.btn_delete)
        dbHelper = DBHelper(this)
        refreshData()
        //Event
        btnAdd.setOnClickListener {
            val person = Person(
                Integer.parseInt(editId.text.toString()),
                editName.text.toString(),
                editEmail.text.toString()
            )
            dbHelper.addPerson(person)
            refreshData()
        }
      btnUpdate.setOnClickListener{
          val person = Person(
              Integer.parseInt(editId.text.toString()),
              editName.text.toString(),
              editEmail.text.toString()
          )
          dbHelper.updatePerson(person)
          refreshData()
      }
        btnDelete.setOnClickListener{
            val person = Person(
                Integer.parseInt(editId.text.toString()),
                editName.text.toString(),
                editEmail.text.toString()
            )
            dbHelper.deletePerson(person)
            refreshData()
        }
    }

    private fun refreshData() {
        editId = findViewById(R.id.edit_id)
        editName = findViewById(R.id.edit_name)
        editEmail = findViewById(R.id.edit_email)
        listPersons = findViewById(R.id.list_persons)
        firstPerson = dbHelper.allPerson
        val adapter = ListViewAdapter(this, firstPerson, editId, editEmail, editName)
        listPersons.adapter = adapter
    }
}