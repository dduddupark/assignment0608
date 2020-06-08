package com.example.suyeon.assignment0608.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.api.NetWorkThread


/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class DetailFragment : Fragment() {

    private val TAG = "DetailFragment"

    companion object {
        fun newInstance(id: String): DetailFragment {

            return DetailFragment().apply {

                val bundle = Bundle()
                bundle.putString("id", id)

                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getString("id")?.let {
            getData(it)
        }
    }

    private fun getData(param: String) {

        Log.d(TAG, "param = " + param)

        NetWorkThread("GET", "employees/{id}", mapOf("id" to param),
            object : NetWorkThread.NetworkFinishListener {
                override fun onFinished(result: String) {
                    Log.d(TAG, "result = " + result)

                }
            }
        ).execute()
    }
}