package com.example.earthquakeinfoapp.data.repository

import com.example.earthquakeinfoapp.data.network.EarthquakeAPI
import javax.inject.Inject

class EarthquakeRepository @Inject constructor(private val api:EarthquakeAPI){

    suspend fun getEarthquakes(formatted: Boolean, north: Double, south: Double, east: Double, west: Double, username: String) =
         api.getEarthquakeInfo(formatted, north, south, east, west, username).earthquakes

}
