package com.example.earthquakeinfoapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Earthquake(
    val datetime: String,
    val depth: Double,
    val lat: Double,
    val lng: Double,
    val magnitude: Double
): Parcelable
