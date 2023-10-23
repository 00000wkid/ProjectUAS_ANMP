    package com.example.projectuts_anmp

    import com.google.gson.annotations.SerializedName


    data class MenuData(
        @SerializedName("appetizers")
        val appetizers: List<MenuItem>,
        @SerializedName("riceAndNoodles")
        val riceAndNoodles: List<MenuItem>
    )