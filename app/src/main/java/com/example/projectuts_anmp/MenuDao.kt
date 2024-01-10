package com.example.projectuts_anmp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.model.Menu

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg menu: List<MenuItem>)

    @Query("SELECT * FROM menu")
    fun selectAllMenus(): List<Menu>

    @Query("SELECT * FROM menu WHERE category = 'Appetizer'")
    fun selectAppetizerMenus(): List<Menu>

    @Query("SELECT * FROM menu WHERE category = 'RiceNoodles'")
    fun selectRiceNoodlesMenus(): List<Menu>

}
