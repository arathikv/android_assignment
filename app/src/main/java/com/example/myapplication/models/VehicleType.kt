package com.example.myapplication.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleType(
    val IsPrimary: Boolean,
    val Name: String
): Parcelable