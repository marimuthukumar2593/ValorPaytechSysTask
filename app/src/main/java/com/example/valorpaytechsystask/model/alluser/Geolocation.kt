package com.example.valorpaytechsystask.model.alluser

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
class Geolocation {
    @SerializedName("lat" ) var lat : String? = null
    @SerializedName("lng" ) var lng : String? = null
}