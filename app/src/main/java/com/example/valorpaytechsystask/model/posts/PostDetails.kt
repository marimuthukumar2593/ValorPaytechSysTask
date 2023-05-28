package com.example.valorpaytechsystask.model.posts

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
class PostDetails() : Parcelable {
    @SerializedName("userId" ) var userId : String?    = null
    @SerializedName("id"     ) var id     : String?    = null
    @SerializedName("title"  ) var title  : String? = null
    @SerializedName("body"   ) var body   : String? = null

    constructor(parcel: Parcel) : this() {
        userId = parcel.readString()
        id = parcel.readString()
        title = parcel.readString()
        body = parcel.readString()
    }



    companion object CREATOR : Parcelable.Creator<PostDetails> {
        override fun createFromParcel(parcel: Parcel): PostDetails {
            return PostDetails(parcel)
        }

        override fun newArray(size: Int): Array<PostDetails?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }
}