package com.example.myapplication.models

import android.os.Parcelable
import com.example.myapplication.models.VehicleType
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
