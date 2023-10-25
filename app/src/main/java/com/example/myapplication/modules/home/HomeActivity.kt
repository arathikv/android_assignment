package com.example.myapplication.modules.home

import Example4Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.R

import com.example.myapplication.modules.home.profile.FragmentProfile
import com.example.myapplication.modules.home.quotes.FragmentQuotes
import com.example.myapplication.modules.home.recyclerview.FragmentRecyclerViewLocalData
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(FragmentProfile())
                    true
                }

                R.id.bottom_quotes -> {
                    replaceFragment(FragmentQuotes())
                    true
                }

                R.id.bottom_views -> {
                    replaceFragment(FragmentRecyclerViewLocalData())
                    true
                }

                R.id.bottom_cars -> {
                    replaceFragment(Example4Fragment())
                    true
                }

                else -> false
            }
        }
        replaceFragment(FragmentProfile())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame1, fragment).commit()
    }
}