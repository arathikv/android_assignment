package com.example.myapplication.modules.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.viewmodels.QuotesViewModel

class FragmentQuotes : Fragment() {
    private lateinit var quotesTextView: TextView
    private lateinit var viewModel: QuotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quotes, container, false)
        quotesTextView = view.findViewById(R.id.quotesTextView)

        viewModel = ViewModelProvider(this).get(QuotesViewModel::class.java)
        viewModel.quotesLiveData.observe(this){
            quotesTextView.text=it.first()
        }
        viewModel.getQuotes()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}