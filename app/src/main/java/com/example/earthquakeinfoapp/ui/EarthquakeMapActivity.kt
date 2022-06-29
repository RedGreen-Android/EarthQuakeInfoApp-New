package com.example.earthquakeinfoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earthquakeinfoapp.R
import com.example.earthquakeinfoapp.data.model.Earthquake
import com.example.earthquakeinfoapp.databinding.ActivityEarthquakeMapBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthquakeMapActivity : AppCompatActivity() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    val earth = Earthquake("123", 20.0, 34.2,12.1,41.2)


    private lateinit var earthquakeMapBinding: ActivityEarthquakeMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        earthquakeMapBinding = ActivityEarthquakeMapBinding.inflate(layoutInflater)
        setContentView(earthquakeMapBinding.root)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            googleMap = it

            val location: LatLng = LatLng(earth.lat, earth.lng) //using default values
            googleMap.addMarker(MarkerOptions().position(location).title("EarthQuake location"))
        }

    }

}