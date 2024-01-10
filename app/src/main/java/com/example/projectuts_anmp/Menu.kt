package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "price")
    var price: Int,
    @ColumnInfo(name = "description")
    var description: String, // Change the type to String
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "image")
    var image: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
