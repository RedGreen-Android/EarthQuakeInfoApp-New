package com.example.earthquakeinfoapp.data.model

data class Earthquake(
    val datetime: String,
    val depth: Double,
    val lat: Double,
    val lng: Double,
    val magnitude: Double,
)
