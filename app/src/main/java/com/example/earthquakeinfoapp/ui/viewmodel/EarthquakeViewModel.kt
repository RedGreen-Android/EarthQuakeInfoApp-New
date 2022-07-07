package com.example.earthquakeinfoapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.earthquakeinfoapp.R
import com.example.earthquakeinfoapp.data.model.Earthquake
import com.example.earthquakeinfoapp.data.repository.EarthquakeRepository
import com.example.earthquakeinfoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakeViewModel @Inject constructor(private val repository: EarthquakeRepository) : ViewModel() {

//    private val _earthQuake: MutableLiveData<List<Earthquake>> = MutableLiveData()
//    val earthQuake: LiveData<List<Earthquake>> = _earthQuake

    fun getEarthQuake(formatted: Boolean, north: Double, south: Double, east: Double, west: Double, username: String) =
        liveData {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(repository.getEarthquakes(formatted, north, south, east, west, username)))
            } catch (exception : Exception) {
                emit(Resource.error(data = null, message = exception.message?: "Error"))
            }
        }
}