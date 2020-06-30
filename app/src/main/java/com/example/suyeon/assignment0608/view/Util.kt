package com.example.suyeon.assignment0608.view

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun View?.setVisibility(isShow: Boolean) {
    if (this == null) return
    else visibility = if (isShow) View.VISIBLE else View.GONE
}

fun Context?.show(text: String) {
    if(this == null) return else Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun FragmentManager?.set(id: Int, newFragment: Fragment) {
    if (this == null) return
    else {
        if (findFragmentById(id) == null) {
            beginTransaction().apply {
                add(id, newFragment)
                commit()
            }
        } else {
            beginTransaction().apply {
                replace(id, newFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}