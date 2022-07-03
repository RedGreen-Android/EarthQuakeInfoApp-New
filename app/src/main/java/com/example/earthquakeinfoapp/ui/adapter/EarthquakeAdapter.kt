package com.example.earthquakeinfoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakeinfoapp.data.model.Earthquake
import com.example.earthquakeinfoapp.databinding.EarthquakeItemBinding
import com.example.earthquakeinfoapp.ui.MyClickListener

class EarthquakeAdapter(private val earthquakes: ArrayList<Earthquake>, val myCallBack: MyClickListener) :
    RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder>(
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val binding = EarthquakeItemBinding.inflate(LayoutInflater.from(parent.context))
        return EarthquakeViewHolder(binding,myCallBack)
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        holder.bind(earthquakes[position])
    }

    class EarthquakeViewHolder(
        private val binding: EarthquakeItemBinding,
        val mycallback: MyClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: Earthquake) {

            binding.earthquake = employee
            binding.carditem.setOnClickListener {
                mycallback.myItemClick(bindingAdapterPosition)
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = earthquakes.size

    fun addUsers(users: List<Earthquake>) {
        this.earthquakes.apply {
            clear()
            addAll(users)
        }
    }

//    companion object {
//        @JvmStatic
//        @BindingAdapter("backgroundcolor")
//        fun backgroundcolor (earthquakemodel: Earthquake): Int {
//            if (earthquakemodel.magnitude >= 8.00) {
//            return R.color.black
//            }
//            else return R.color.purple_200
//        }
//    }
}