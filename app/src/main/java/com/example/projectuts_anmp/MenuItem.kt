package com.example.projectuts_anmp

import android.os.Parcel
import android.os.Parcelable

data class MenuItem(val id:Int, val name: String, val price: Double, val imageUrl: String, val description: String,
                    var quantity:Int=0)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt()?:0,
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()?:0
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
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