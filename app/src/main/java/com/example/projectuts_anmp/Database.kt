package com.example.projectuts_anmp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper


class Database(context: Context?, name: String?, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}