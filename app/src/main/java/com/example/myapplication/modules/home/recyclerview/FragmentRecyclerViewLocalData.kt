package com.example.myapplication.modules.home.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.RecyclerViewDataItems

class FragmentRecyclerViewLocalData : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private lateinit var recyclerView: RecyclerView
    private val data = ArrayList<RecyclerViewDataItems>()
    private lateinit var adapter: CustomAdapter

    private var currentPage = 0
    private val itemsPerPage = 5
    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_view_local_data, container, false)

        // Getting the RecyclerView by its ID
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        // This creates a vertical layout manager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ArrayList of class ItemsViewModel
        val data = ArrayList<RecyclerViewDataItems>()

        // This loop will create 20 views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(RecyclerViewDataItems(R.drawable.ic_baseline_folder_24, "Folder $i"))
        }

        // This will pass the ArrayList to our Adapter
        adapter = CustomAdapter(data)

        // Setting the Adapter with the RecyclerView
        recyclerView.adapter = adapter
        // Add an OnScrollListener to handle pagination
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
//                        && totalItemCount >= itemsPerPage
//                    ) {
//                        loadMoreItems()
//                    }
//                }
//            }
//        })
//        loadMoreItems()
        return view

    }
    private fun loadMoreItems() {
        // Simulate loading new items. You should replace this with your data loading logic.
        val newItems = ArrayList<RecyclerViewDataItems>()
        for (i in 1..itemsPerPage) {
            val nextItemIndex = currentPage * itemsPerPage + i
            if (nextItemIndex < 20) {
                newItems.add(RecyclerViewDataItems(R.drawable.ic_baseline_folder_24, "Folder $nextItemIndex"))
            }
        }

        data.addAll(newItems)
        adapter.notifyDataSetChanged()

        currentPage++
        isLoading = false

        if (data.size >= 20) {
            isLastPage = true
        }
    }

    companion object {
        @JvmStatic fun newInstance() = FragmentRecyclerViewLocalData()
    }
}