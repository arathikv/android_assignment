package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Example3Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_example3, container, false)

        // Getting the RecyclerView by its ID
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        // This creates a vertical layout manager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, "Folder $i"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the RecyclerView
        recyclerView.adapter = adapter

        return view

    }

    companion object {
        @JvmStatic fun newInstance() = Example3Fragment()
    }
}