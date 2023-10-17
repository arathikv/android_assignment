package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(private val mList: List<CarResult>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        val carResult= mList[position]
        holder.textView1.text = carResult.Mfr_CommonName
        holder.textView.text = carResult.Country

        println(carResult)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView1: TextView = itemView.findViewById(R.id.txtcarname)

        val textView: TextView = itemView.findViewById(R.id.txtcarCountry)
    }
}
