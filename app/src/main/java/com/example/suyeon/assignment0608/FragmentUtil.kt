package com.example.suyeon.assignment0608

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

fun setFragment(id: Int, newFragment: Fragment, fragmentManager: FragmentManager) {

    val transaction = if (fragmentManager.findFragmentById(id) == null) {
        fragmentManager.beginTransaction().apply {
            add(id, newFragment)
        }
    } else {
        fragmentManager.beginTransaction().apply {
            replace(id, newFragment)
            addToBackStack(null)
        }
    }

    // Commit the transaction
    transaction.commit()
}