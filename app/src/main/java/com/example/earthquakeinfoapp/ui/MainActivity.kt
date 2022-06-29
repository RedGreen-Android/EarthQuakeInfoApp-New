package com.example.earthquakeinfoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakeinfoapp.data.model.Earthquake
import com.example.earthquakeinfoapp.databinding.ActivityMainBinding
import com.example.earthquakeinfoapp.ui.adapter.EarthquakeAdapter
import com.example.earthquakeinfoapp.ui.viewmodel.EarthquakeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val earthquakeViewModel: EarthquakeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    lateinit var earthquakeAdapter: EarthquakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserver()
    }

    fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        earthquakeAdapter = EarthquakeAdapter(arrayListOf())
        binding.recyclerView.adapter = earthquakeAdapter
    }

    fun onListClick(view: View){
        val intent = Intent(this, EarthquakeMapActivity::class.java)
        startActivity(intent)
    }

    fun setupObserver(){
        earthquakeViewModel.getEarthQuake(true, 44.1, -9.9, -22.4, 55.2, "mkoppelman")
        earthquakeViewModel.earthQuake.observe(this, Observer { response ->
            retrieveEmployeeList(response)
            })
    }

    private fun retrieveEmployeeList(users: List<Earthquake>) {
        earthquakeAdapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }

}


}