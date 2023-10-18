package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [CarDetailsFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
class CarDetailsFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_car_details, container, false)

        println(arguments)
        val carData = arguments?.getSerializable("carData") as CarResult?

        val country = carData?.Country
        val Mfr_CommonName = carData?.Mfr_CommonName
        val Mfr_ID = carData?.Mfr_ID
        val Mfr_Name = carData?.Mfr_Name

        println(country)

        val Country1 = view?.findViewById<TextView>(R.id.Country)
        val CommonName1 = view?.findViewById<TextView>(R.id.Mfr_CommonName)
        val ID1 = view?.findViewById<TextView>(R.id.Mfr_ID)
        val Name1 = view?.findViewById<TextView>(R.id.Mfr_Name)

        Country1?.text = country
        CommonName1?.text = Mfr_CommonName
        ID1?.text = Mfr_ID.toString()
        Name1?.text = Mfr_Name
        // Inflate the layout for this fragment
        return view

    }

    companion object {

        @JvmStatic
        fun newInstance(carData: CarResult): CarDetailsFragment {
            val fragment = CarDetailsFragment()

            val args = Bundle()
//            args.putParcelable("carData", carData) // Use putParcelable if your object is Parcelable
            args.putSerializable("carData", carData)

            fragment.arguments = args
            println(carData.Country)

            return fragment
        }
//        fun newInstance(param1: String, param2: String) =
//            CarDetailsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}