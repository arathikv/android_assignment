package com.example.myapplication.modules.home.cars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.VehicleType

class VehicleTypeAdapter(private val vehicleTypeList: List<VehicleType>) :
    RecyclerView.Adapter<VehicleTypeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vehicle_type_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carResult = vehicleTypeList[position]
        println(carResult)
        holder.vehicleType.text = carResult.Name
//        holder.type2.text=carResult.IsPrimary.toString()
    }

    override fun getItemCount(): Int {
        return vehicleTypeList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val vehicleType: TextView = itemView.findViewById(R.id.vehicleType)
//        val type2: TextView = itemView.findViewById(R.id.type2)
    }
}
