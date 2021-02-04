package com.example.sqllitedemokt.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import com.example.sqllitedemokt.Model.Person
import com.example.sqllitedemokt.R

class ListViewAdapter(
    internal var activity: Activity,
    internal var firstPerson: List<Person>,
    internal var edt_id: EditText,
    internal var edt_email: EditText,
    internal var edt_name: EditText, ):BaseAdapter() {

    internal var inflater: LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView: View
        rootView = inflater.inflate(R.layout.row_layout,null)
        var txtRowId = rootView.findViewById<View>(R.id.txt_row_id) as TextView
        var txtRowName = rootView.findViewById<View>(R.id.txt_name) as TextView
        var txtRowEmail = rootView.findViewById<View>(R.id.txt_email) as TextView
        txtRowId.text = firstPerson[position].id.toString()
        txtRowName.text = firstPerson[position].name.toString()
        txtRowEmail.text = firstPerson[position].email.toString()
        // Event
        rootView.setOnClickListener{
            edt_id.setText(txtRowId.text.toString())
            edt_name.setText(txtRowName.text.toString())
            edt_email.setText(txtRowEmail.text.toString())
        }
        return rootView
    }
    override fun getItem(position: Int): Any {
        return firstPerson[position]
    }

    override fun getItemId(position: Int): Long {
       return firstPerson[position].id.toLong()
    }

    override fun getCount(): Int {
     return firstPerson.size
    }

}