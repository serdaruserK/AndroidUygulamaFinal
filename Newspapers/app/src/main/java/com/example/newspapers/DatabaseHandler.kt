package com.example.newspapers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASENAME = "UsersDB"
val TABLENAME = "Users"
val COL_MAİL = "mail"
val COL_PASS = "pass"
val COL_ID = "id"


class DatabaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + TABLENAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MAİL + " VARCHAR(25), " + COL_PASS + " VARCHAR(25) )"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun updateData(user: User) {
        val db = this.writableDatabase
        db.execSQL("UPDATE TABLE UserDB SET  name= '${user.mail}', surname = '${user.pass}' WHERE id = (SELECT MAX(id) FROM UserDB) ")
        val contentValues = ContentValues()
        contentValues.put(COL_MAİL, user.mail)
        contentValues.put(COL_PASS, user.pass)

    }
    fun deleteData() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLENAME WHERE id = (SELECT MAX(id) FROM $TABLENAME)")
        db.close()
    }


    fun insertData(user: User) {

        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_MAİL, user.mail)
        contentValues.put(COL_PASS, user.pass)

        val result = database.insert(TABLENAME, null, contentValues)

        if (result == 0L) {
            Toast.makeText(context, "Kayıt Başarısız, Tekrar Deneyiniz:(", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Giriş Başarılı..! HOŞGELDİNİZ!", Toast.LENGTH_SHORT).show()
        }

    }

    fun readData():MutableList<User>{

        val list : MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query,null)

        if(result.moveToFirst()){

            do{

                val id : Int = result.getString(result.getColumnIndex(COL_ID)).toInt()
                val mail : String = result.getString(result.getColumnIndex(COL_MAİL))
                val pass : Int = result.getString(result.getColumnIndex(COL_PASS)).toInt()

                val user  = User(id,mail,pass)
                list.add(user)
            }while (result.moveToNext())

        }

        return list
    }

}