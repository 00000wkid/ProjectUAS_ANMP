package com.example.projectuts_anmp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("dfjkk", "onCreate called")
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                DESC_COL + " TEXT," +
                PRICE_COL + " DOUBLE," +
                QTY_COL + " INTEGER," +
                CATEGORY_COL + " TEXT," +
                IMAGE_COL + " TEXT" + ")")  // Added a comma here
        db?.execSQL(query)
    //create table cart
        val query2 = ("CREATE TABLE "+TABLE_NAME2+" ("
                + ID_COL2 + " INTEGER PRIMARY KEY, " +
                NAME_COl2 + " TEXT," +
                DESC_COL2 + " TEXT," +
                PRICE_COL2 + " DOUBLE," +
                QTY_COL2 + " INTEGER," +
                CATEGORY_COL2 + " TEXT," +
                IMAGE_COL2 + " TEXT" + ")")  // Added a comma here
        db?.execSQL(query2)
        Log.d("dfjkOnCreate", "onCreate: $query")
        insertInitialData(db)

    }
    private fun insertInitialData(db: SQLiteDatabase?) {
        db?.let {
            addMenu(it, "Fried Chicken", "Fried chicken is a dish consisting of chicken pieces usually from broiler chickens which have been floured or battered and then pan-fried, deep fried, or pressure fried. The breading adds a crispy ", 2.1, 1, "Appetizer", "a")
            addMenu(it, "Fried Rice", "Fried rice is a dish of cooked rice that has been stir-fried in a wok or a frying pan and is usually mixed with other ingredients such as eggs, vegetables, seafood, or meat.", 2.3, 2, "Rice & Noodles", "b")
            addMenu(it, "Fried Noodles", "Fried noodles are a dish made from noodles, vegetables and other ingredients that are stir fried on high heat.", 2.2, 3, "Rice & Noodles", "c")
            addMenu(it, "Fried Rice", "Fried rice is a dish of cooked rice that has been stir-fried in a wok or a frying pan and is usually mixed with other ingredients such as eggs, vegetables, seafood, or meat.", 2.2, 4, "Rice & Noodles", "d")
        }
    }



    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    private fun addMenu(
        db: SQLiteDatabase,
        name:String, desc: String, price: Double,
        qty:Int,
        category:String, image: String ){

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
    fun getCart(name: String?): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM cart where name LIKE '%$name%'", null)
    }
    fun addCart(
        name:String, desc: String, price: Double,
        qtyy:Int,
        category:String, image: String ) {
        val db = this.readableDatabase
        val values = ContentValues()
        values.put(NAME_COl2, name)
        values.put(DESC_COL2, desc)
        values.put(PRICE_COL2, price)
        values.put(QTY_COL2, qtyy)
        values.put(CATEGORY_COL2, category)
        values.put(IMAGE_COL2, image)
        //check first if data exist
        val cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME2+" WHERE name='$name'", null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            val qty = cursor.getInt(cursor.getColumnIndex(DBHelper.QTY_COL2))
            values.put(QTY_COL2, qty + qtyy)
            db.update(TABLE_NAME2, values, "name='$name'", null)
        } else
        {
            db.insert(TABLE_NAME2, null, values)
        }
        db.close()
    }

    fun getCart(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM cart", null)
    }


    companion object{
        private val DATABASE_NAME = "PROJECT_UAS"
        private val DATABASE_VERSION = 1


        val TABLE_NAME = "menu"

        // below is the variable for id column
        val ID_COL = "id"
        val NAME_COl = "name"
        val DESC_COL = "desc"
        val PRICE_COL = "price"
        val IMAGE_COL = "image"
        val QTY_COL = "qty"
        val CATEGORY_COL = "category"

        //cart
        val TABLE_NAME2 = "cart"
        val ID_COL2 = "id"
        val NAME_COl2 = "name"
        val DESC_COL2 = "desc"
        val PRICE_COL2 = "price"
        val IMAGE_COL2 = "image"
        val QTY_COL2 = "qty"
        val CATEGORY_COL2 = "category"


    }
}
