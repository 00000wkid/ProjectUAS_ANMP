package com.example.projectuts_anmp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.model.Menu

@Database(entities = arrayOf(Menu::class), version = 1)
abstract class MenuDatabase:RoomDatabase() {
    abstract fun MenuDao(): MenuDao
    companion object {
        @Volatile private var instance: MenuDatabase ?= null
        private val LOCK = Any()
        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MenuDatabase::class.java,
                "menu_db").build()
        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
