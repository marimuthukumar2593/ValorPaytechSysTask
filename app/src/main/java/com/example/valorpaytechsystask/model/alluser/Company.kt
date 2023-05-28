package com.example.valorpaytechsystask.model.alluser

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
class Company {
    @SerializedName("name"        ) var name        : String? = null
    @SerializedName("catchPhrase" ) var catchPhrase : String? = null
    @SerializedName("bs"          ) var bs          : String? = null
}