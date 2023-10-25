package com.example.myapplication.modules.home.cars

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

class FragmentCarList : Fragment() {
    private lateinit var viewModel :CarViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarAdapter
    private var isScrolling = false
    private var pageSize=10

    private lateinit var originalCarDataList: List<CarResult>
    private lateinit var tempCarDataList: List<CarResult>
    private lateinit var progressBar: ProgressBar
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

        // Load initial data

        viewModel.carLiveData.observe(this,  {
            originalCarDataList=it.Results
            tempCarDataList= it.Results.subList(0, minOf(10,it.Results.size))

            adapter = CarAdapter(tempCarDataList)

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

            recyclerView.adapter=adapter
            progressBar.visibility = View.GONE
        })

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

        viewModel.makeCarApiCall()

        return view
    }

    private fun loadMoreItems() {
        progressBar = view?.findViewById(R.id.progressBar) !!
        var pageStart= tempCarDataList.size
        var pageEnd= minOf(pageStart+pageSize,originalCarDataList.size)
        progressBar.visibility = View.VISIBLE
        tempCarDataList+= originalCarDataList.subList(pageStart,pageEnd)

        handler.postDelayed({
            adapter.updateData(tempCarDataList)
            progressBar.visibility = View.GONE
        }, 2000)

    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCarList()
    }
}
