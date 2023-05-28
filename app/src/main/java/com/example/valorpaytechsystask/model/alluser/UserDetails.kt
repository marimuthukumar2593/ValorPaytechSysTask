package com.example.valorpaytechsystask.model.alluser

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
class UserDetails() :Parcelable{
    @SerializedName("id"       ) var id       : String?     = null
    @SerializedName("name"     ) var name     : String?  = null
    @SerializedName("username" ) var username : String?  = null
    @SerializedName("email"    ) var email    : String?  = null
    @SerializedName("address"  ) var address  : Address? = Address()
    @SerializedName("phone"    ) var phone    : String?  = null
    @SerializedName("website"  ) var website  : String?  = null
    @SerializedName("company"  ) var company  : Company? = Company()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        username = parcel.readString()
        email = parcel.readString()
        phone = parcel.readString()
        website = parcel.readString()
    }



    companion object CREATOR : Parcelable.Creator<UserDetails> {
        override fun createFromParcel(parcel: Parcel): UserDetails {
            return UserDetails(parcel)
        }

        override fun newArray(size: Int): Array<UserDetails?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(website)
    }

    override fun describeContents(): Int {
        return 0
    }
}