package com.example.projectuts_anmp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.google.gson.Gson

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("dfjkk", "onCreate called")
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                DESC_COL + " TEXT," +
                PRICE_COL + " TEXT," +
                QTY_COL + " INTEGER," +
                CATEGORY_COL + " TEXT," +
                IMAGE_COL + " TEXT" + ")")  // Added a comma here
        db?.execSQL(query)
        Log.d("dfjkOnCreate", "onCreate: $query")
        insertInitialData(db)

    }
    private fun insertInitialData(db: SQLiteDatabase?) {
        db?.let {
            addMenu(it, "Fried Chicken", "Fried chicken is a dish consisting of chicken pieces usually from broiler chickens which have been floured or battered and then pan-fried, deep fried, or pressure fried. The breading adds a crispy ", "Rp. 20.000", 0, "Appetizer", "")
            addMenu(it, "Fried Rice", "Fried rice is a dish of cooked rice that has been stir-fried in a wok or a frying pan and is usually mixed with other ingredients such as eggs, vegetables, seafood, or meat.", "Rp. 20.000", 0, "Rice & Noodles", "")
            addMenu(it, "Fried Noodles", "Fried noodles are a dish made from noodles, vegetables and other ingredients that are stir fried on high heat.", "Rp. 20.000", 0, "Rice & Noodles", "")
            addMenu(it, "Fried Rice", "Fried rice is a dish of cooked rice that has been stir-fried in a wok or a frying pan and is usually mixed with other ingredients such as eggs, vegetables, seafood, or meat.", "Rp. 20.000", 0, "Rice & Noodles", "")
        }
    }



    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    private fun addMenu(db: SQLiteDatabase,name:String, desc : String, price : String,qty:Int,category:String, image : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COl, name)
        values.put(DESC_COL, desc)
        values.put(PRICE_COL, price)
        values.put(QTY_COL, qty)
        values.put(CATEGORY_COL, category)
        values.put(IMAGE_COL, image)
//       val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
//        db.close()
        //check if data inserted
        Log.d("dfjkfff", "addMenu: $values")
    }


    fun getMenu(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM menu", null)

    }
    fun getAppetizer(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM menu WHERE category='Appetizer'", null)

    }
    fun getRiceNoodles(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM menu WHERE category='Rice & Noodles'", null)

    }


    companion object{
        private val DATABASE_NAME = "PROJECT_UAS"
        private val DATABASE_VERSION = 1


        val TABLE_NAME = "menu"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val NAME_COl = "name"
        val DESC_COL = "desc"
        val PRICE_COL = "price"
        val IMAGE_COL = "image"
        val QTY_COL = "qty"
        val CATEGORY_COL = "category"


    }
}
