package com.example.valorpaytechsystask.model.comments

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
class Comments {
    @SerializedName("postId" ) var postId : Int?    = null
    @SerializedName("id"     ) var id     : Int?    = null
    @SerializedName("name"   ) var name   : String? = null
    @SerializedName("email"  ) var email  : String? = null
    @SerializedName("body"   ) var body   : String? = null
}