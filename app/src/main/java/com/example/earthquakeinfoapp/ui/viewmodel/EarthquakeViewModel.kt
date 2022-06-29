package com.example.earthquakeinfoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakeinfoapp.R
import com.example.earthquakeinfoapp.data.model.Earthquake
import com.example.earthquakeinfoapp.data.repository.EarthquakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakeViewModel @Inject constructor(private val repository: EarthquakeRepository) : ViewModel() {

//    val mapEarthquake: MutableLiveData<Double> = MutableLiveData()

    private val _earthQuake: MutableLiveData<List<Earthquake>> = MutableLiveData()
    val earthQuake: LiveData<List<Earthquake>> = _earthQuake

    fun getEarthQuake(formatted: Boolean, north: Double, south: Double, east: Double, west: Double, username: String){
        viewModelScope.launch {
            val response = repository.getEarthquakes(formatted, north, south, east, west, username)
            _earthQuake.value = response
        }
    }

//    fun onEarthquakeClick() {
//        mapEarthquake.postValue(earthquake.lat)
//    }

}