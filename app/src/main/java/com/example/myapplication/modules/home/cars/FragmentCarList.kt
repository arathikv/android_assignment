//package com.example.myapplication.modules.home.cars
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelStoreOwner
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.myapplication.R
//import com.example.myapplication.models.CarResult
//import com.example.myapplication.network.CarInterface
//import com.example.myapplication.viewmodels.CarViewModel
//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class Example4Fragment : Fragment() {
//    private lateinit var viewModel: CarViewModel<Any?>
//    private var isLoading = false
//    private var isLastPage = false
//    private var currentPage = 1
//    private lateinit var adapter: CarAdapter
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    @OptIn(DelicateCoroutinesApi::class)
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val view = inflater.inflate(R.layout.fragment_car_list, container, false)
//        val recyclerView1 = view.findViewById<RecyclerView>(R.id.recyclerview1)
//        recyclerView1.layoutManager = LinearLayoutManager(requireContext())
//
//        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)
//        viewModel.carLiveData.observe(this) {
//             adapter = CarAdapter(it.Results)
//
//
//            //item click move to new fragment
//            // Set an item click listener for the adapter
//            adapter?.setOnItemClickListener(object : CarAdapter.OnItemClickListener {
//                override fun onItemClick(carData: CarResult) {
//                    // Handle the click event here
//                    val anotherFragment = FragmentCarDetails.newInstance(carData)
//                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
//                    transaction.replace(R.id.frame1, anotherFragment)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//                }
//            })
//
//            recyclerView1.adapter = adapter
//        }
//        // Implement pagination when scrolling
//        recyclerView1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val visibleItemCount = layoutManager.childCount
//                val totalItemCount = layoutManager.itemCount
//                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//
//                if (!isLoading && !isLastPage) {
//                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
//                        && firstVisibleItemPosition >= 0
//                    ) {
//                        loadMoreItems()
//                    }
//                }
//            }
//        })
//
//        // Load initial data
//        viewModel.carLiveData.observe(this) {
//            adapter.updateData(it.Results)
//            currentPage++
//        }
//        viewModel.makeCarApiCall()
//
//        viewModel.makeCarApiCall()
//        return view
//    }
//    private fun loadMoreItems() {
//        isLoading = true
//        // You can show a loading indicator here
//
//        viewModel.makeCarApiCall(page = currentPage)
//
//        viewModel.carLiveData.observe(this) {
//            // Remove the loading indicator if it was added
//            // Update the adapter with the new data
//            adapter.updateData(it.Results)
//            isLoading = false
//            currentPage++
//        }
//    }
//    companion object {
//        @JvmStatic
//        fun newInstance() = Example4Fragment()
//    }
//}
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CarResult
import com.example.myapplication.modules.home.cars.CarAdapter
import com.example.myapplication.modules.home.cars.FragmentCarDetails
import com.example.myapplication.viewmodels.CarViewModel

class Example4Fragment : Fragment() {
    private lateinit var viewModel :CarViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarAdapter
    private var isScrolling = false

    private var pageSize=10

    private lateinit var originalCarDataList: List<CarResult>
    private lateinit var tempCarDataList: List<CarResult>

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_car_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerview1)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val progressBar = view.findViewById(R.id.progressBar) as ProgressBar

        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        // Initialize your adapter with an empty list
        adapter = CarAdapter(mutableListOf())

        // Set an item click listener for the adapter
        adapter.setOnItemClickListener(object : CarAdapter.OnItemClickListener {
            override fun onItemClick(carData: CarResult) {
                val anotherFragment = FragmentCarDetails.newInstance(carData)
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame1, anotherFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })

        recyclerView.adapter = adapter

        // Implement pagination when scrolling
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if(isScrolling && (visibleItemCount + firstVisibleItemPosition)>=totalItemCount){

                    isScrolling=false
                    loadMoreItems()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true
                }
            }
        })

        // Load initial data

        viewModel.carLiveData.observe(this,  {
            // Use carResults directly to update the adapter

            originalCarDataList=it.Results
            tempCarDataList= it.Results.subList(0, minOf(10,it.Results.size))

            adapter = CarAdapter(tempCarDataList)

            recyclerView.adapter=adapter
            progressBar.visibility = View.GONE
        })

        viewModel.makeCarApiCall()

        return view
    }

    private fun loadMoreItems() {

        var pageStart= tempCarDataList.size
        var pageEnd= minOf(pageStart+pageSize,originalCarDataList.size)

        tempCarDataList+= originalCarDataList.subList(pageStart,pageEnd)

        handler.postDelayed({
            adapter.updateData(tempCarDataList)
        }, 2000)

    }

    companion object {
        @JvmStatic
        fun newInstance() = Example4Fragment()
    }
}
