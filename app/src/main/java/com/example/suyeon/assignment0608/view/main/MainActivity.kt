package com.example.suyeon.assignment0608.view.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.setFragment

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")

        setFragment(R.id.fragment_container, MainFragment(), supportFragmentManager)
    }
}