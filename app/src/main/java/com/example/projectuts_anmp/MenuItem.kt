package com.example.projectuts_anmp

import android.os.Parcel
import android.os.Parcelable

data class MenuItem(
    val id:Int, val name: String, val price: Double, val imageUrl: String, val description: String,
    var qty: Int = 0, var category: String = "")
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt()?:0,
        parcel.readString() ?: "",
        parcel.readDouble()?:0.0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()?:0,
        parcel.readString() ?: ""


    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeInt(qty)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuItem> {
        override fun createFromParcel(parcel: Parcel): MenuItem {
            return MenuItem(parcel)
        }

        override fun newArray(size: Int): Array<MenuItem?> {
            return arrayOfNulls(size)
        }
    }
}