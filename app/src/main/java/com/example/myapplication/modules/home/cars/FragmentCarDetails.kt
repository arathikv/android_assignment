package com.example.myapplication.modules.home.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CarResult

class FragmentCarDetails : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_car_details, container, false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview_vehicle_type)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())



        println(arguments)
        val carData = arguments?.getParcelable("carData") as CarResult?


        val vehicleType=carData?.VehicleTypes
        val adapter = vehicleType?.let { VehicleTypeAdapter(it) }
        recyclerview.adapter = adapter


        val country = carData?.Country
        val Mfr_CommonName = carData?.Mfr_CommonName
        val Mfr_ID = carData?.Mfr_ID
        val Mfr_Name = carData?.Mfr_Name

        println(country)

        val Country1 = view?.findViewById<TextView>(R.id.Country)
        val CommonName1 = view?.findViewById<TextView>(R.id.Mfr_CommonName)
        val ID1 = view?.findViewById<TextView>(R.id.Mfr_ID)
        val Name1 = view?.findViewById<TextView>(R.id.Mfr_Name)

        Country1?.text = "Country : $country"
        CommonName1?.text = "Mfr_CommonName : $Mfr_CommonName"
        ID1?.text = "Mfr_ID : $Mfr_ID"
        Name1?.text = "Mfr_Name : $Mfr_Name"
        // Inflate the layout for this fragment
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(carData: CarResult): FragmentCarDetails {
            val fragment = FragmentCarDetails()

            val args = Bundle()
            //doing parcelable

            args.putParcelable("carData", carData) // Use putParcelable if your object is Parcelable
//            args.putSerializable("carData", carData)

            fragment.arguments = args
            println(carData.Country)

            return fragment
        }

    }
}