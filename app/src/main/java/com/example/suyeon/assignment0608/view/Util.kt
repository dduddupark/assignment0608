package com.example.suyeon.assignment0608.view

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Context.show(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun FragmentManager.set(id: Int, newFragment: Fragment) {
    if (this.findFragmentById(id) == null) {
        this.beginTransaction().apply {
            add(id, newFragment)
            commit()
        }
    } else {
        this.beginTransaction().apply {
            replace(id, newFragment)
            addToBackStack(null)
            commit()
        }
    }
}