package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarResult(
    val Country: String?,
    val Mfr_CommonName: String?,
    val Mfr_ID: Int,
    val Mfr_Name: String?,
    val VehicleTypes: List<VehicleType>
) : Parcelable

//doing parceable
