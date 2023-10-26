package com.example.myapplication.modules.home.cars

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CarResult

class CarAdapter(private var mList: List<CarResult>) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(carData: CarResult)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carResult = mList[position]
        holder.carNameTextView.text = if(carResult.Mfr_CommonName!=null)carResult.Mfr_CommonName
                                        else carResult.Mfr_Name
        holder.carCountryTextView.text = carResult.Country
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
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<CarResult>) {
        mList=newData
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val carNameTextView: TextView = itemView.findViewById(R.id.txtCarName)
        val carCountryTextView: TextView = itemView.findViewById(R.id.txtCarCountry)
    }
}


