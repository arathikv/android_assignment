package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

data class CarResult(
    val Country: String?,
    val Mfr_CommonName: String?,
    val Mfr_ID: Int,
    val Mfr_Name: String?,
    val VehicleTypes: List<VehicleType>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        TODO("VehicleTypes")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Country)
        parcel.writeString(Mfr_CommonName)
        parcel.writeInt(Mfr_ID)
        parcel.writeString(Mfr_Name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CarResult> {
        override fun createFromParcel(parcel: Parcel): CarResult {
            return CarResult(parcel)
        }

        override fun newArray(size: Int): Array<CarResult?> {
            return arrayOfNulls(size)
        }
    }
}

//doing parceable
