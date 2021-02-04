package com.example.sqllitedemokt

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqllitedemokt.Model.Person

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "MGMTDB.db"
        private val DATABASE_VERSION = 1

        // Table
        private val TABLE_NAME = "Person"
        private val COL_ID = "id"
        private val COL_NAME = "Name"
        private val COL_EMAIL = "Email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String =
            ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME TEXT, $COL_EMAIL TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    // CRUD
    val allPerson: List<Person>
        get() {
            val firstPerson = ArrayList<Person>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val person = Person()
                    person.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    person.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    person.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                    firstPerson.add(person)
                } while (cursor.moveToNext())
            }
            db.close()
            return firstPerson
        }

    fun addPerson(person: Person) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, person.id)
        values.put(COL_NAME, person.name)
        values.put(COL_EMAIL, person.email)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updatePerson(person: Person): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, person.id)
        values.put(COL_NAME, person.name)
        values.put(COL_EMAIL, person.email)
        return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(person.id.toString()))
    }

    fun deletePerson(person: Person) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"$COL_ID=?", arrayOf(person.id.toString()))
        db.close()
    }
}