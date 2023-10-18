package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class VehicleAdapter (private val mList: List<VehicleType>) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vehicle_type_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carResult= mList[position]
        println(carResult)
        holder.type1.text = carResult.Name
//        holder.type2.text=carResult.IsPrimary.toString()
//        holder.type2.text = carResult.Country
//        holder.type3.text = carResult.Country

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val type1: TextView = itemView.findViewById(R.id.type1)
//        val type2: TextView = itemView.findViewById(R.id.type2)
//        val type3: TextView = itemView.findViewById(R.id.type3)
    }
}
