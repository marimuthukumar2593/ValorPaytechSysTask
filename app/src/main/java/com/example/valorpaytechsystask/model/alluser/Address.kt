package com.example.valorpaytechsystask.model.alluser

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
class Address {
    @SerializedName("street"  ) var street  : String? = null
    @SerializedName("suite"   ) var suite   : String? = null
    @SerializedName("city"    ) var city    : String? = null
    @SerializedName("zipcode" ) var zipcode : String? = null
    @SerializedName("geo"     ) var geo     : Geolocation?    = Geolocation()
}