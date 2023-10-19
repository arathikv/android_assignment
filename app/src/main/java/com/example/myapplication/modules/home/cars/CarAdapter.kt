package com.example.myapplication.modules.home.cars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CarResult

class CarAdapter(private val mList: List<CarResult>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    //
    private var itemClickListener: OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(carData: CarResult)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        itemClickListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carResult= mList[position]
        holder.textView1.text = carResult.Mfr_CommonName
        holder.textView.text = carResult.Country
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(carResult)
        }
//        holder.itemView.setOnClickListener{
//            onItemClicked(carResult)
//        }
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
