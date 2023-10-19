package com.example.myapplication.modules.home.cars

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CarResult
import com.example.myapplication.network.CarInterface
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val CAR_BASE_URL = "https://vpic.nhtsa.dot.gov/"

class Example4Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_car_list, container, false)
        val recyclerView1 = view.findViewById<RecyclerView>(R.id.recyclerview1)
        recyclerView1.layoutManager = LinearLayoutManager(requireContext())

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CAR_BASE_URL)
            .build()
            .create(CarInterface::class.java)


        GlobalScope.launch(Dispatchers.Main) {
            val retrofitData = retrofitBuilder.getData()
            Log.d("CAR RESPONSE : ", retrofitData.Results.toString())
            val adapter = CarAdapter(retrofitData.Results)

//item click move to new fragment

            // Set an item click listener for the adapter
            adapter.setOnItemClickListener(object : CarAdapter.OnItemClickListener {
                override fun onItemClick(carData: CarResult) {
                    // Handle the click event here
                    val anotherFragment = FragmentCarDetails.newInstance(carData)
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame1, anotherFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            })

            recyclerView1.adapter = adapter

        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = Example4Fragment()

    }


}