package com.example.earthquakeinfoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.earthquakeinfoapp.R
import com.example.earthquakeinfoapp.data.model.Earthquake
import com.example.earthquakeinfoapp.databinding.ActivityEarthquakeMapBinding
import com.example.earthquakeinfoapp.ui.viewmodel.EarthquakeViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthquakeMapActivity : AppCompatActivity() {

    private val earthquakeViewModel: EarthquakeViewModel by viewModels()

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap

    private lateinit var earthquakeMapBinding: ActivityEarthquakeMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        earthquakeMapBinding = ActivityEarthquakeMapBinding.inflate(layoutInflater)
        setContentView(earthquakeMapBinding.root)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            googleMap = it

            earthquakeViewModel.getEarthQuake(true, 44.1, -9.9, -22.4, 55.2, "mkoppelman")
                .observe(this, Observer {
                    var getLocation: Earthquake = intent.extras?.getParcelable("key")!!
                    var location = LatLng(getLocation.lat, getLocation.lng)
                    Log.d("position", location.toString())
                    googleMap.addMarker(MarkerOptions().position(location).title("EarthQuake location"))
                    //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, DEFAULT_))
                })
        }
    }
}
