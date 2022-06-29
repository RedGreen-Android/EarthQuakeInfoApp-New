package com.example.earthquakeinfoapp.data.network

import com.example.earthquakeinfoapp.data.model.Earthquakes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakeAPI {

    //http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman

    @GET("earthquakesJSON")
    suspend fun getEarthquakeInfo( //I know I can use a QUERYMAP as well
        @Query("formatted") formatted: Boolean,
        @Query("north") north : Double,
        @Query("south") south : Double,
        @Query("east") east : Double,
        @Query("west") west : Double,
        @Query("username") username : String

    ) : Earthquakes
}