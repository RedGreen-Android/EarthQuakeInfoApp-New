package com.example.earthquakeinfoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakeinfoapp.data.model.Earthquake
import com.example.earthquakeinfoapp.databinding.ActivityMainBinding
import com.example.earthquakeinfoapp.ui.adapter.EarthquakeAdapter
import com.example.earthquakeinfoapp.ui.viewmodel.EarthquakeViewModel
import com.example.earthquakeinfoapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MyClickListener {

    private val earthquakeViewModel: EarthquakeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    lateinit var earthquakeAdapter: EarthquakeAdapter
    val earthquakeList : ArrayList<Earthquake> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserver()
    }

    fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        earthquakeAdapter = EarthquakeAdapter(arrayListOf(),this)
        binding.recyclerView.adapter = earthquakeAdapter
    }

//    fun onListClick(view: View){
//        val intent = Intent(this, EarthquakeMapActivity::class.java)
//        startActivity(intent)
//    }

    fun setupObserver(){
        earthquakeViewModel.getEarthQuake(true, 44.1, -9.9, -22.4, 55.2, "mkoppelman").observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { users ->
                            retrieveEmployeeList(users)
                        }
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING-> {
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                        }
                    Status.ERROR-> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun retrieveEmployeeList(users: List<Earthquake>) {
        earthquakeList.addAll(users)
        earthquakeAdapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

    override fun myItemClick(position: Int) {
        if (earthquakeList.size> position){
            val model = earthquakeList[position]
            val bundle = Bundle()
            bundle.putParcelable("key", model)
            val intent = Intent(this, EarthquakeMapActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}

interface MyClickListener{
    fun myItemClick(position: Int)

}