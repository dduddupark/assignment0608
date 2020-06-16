package com.example.suyeon.assignment0608.view.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.api.HttpMethod
import com.example.suyeon.assignment0608.api.NetWorkThread
import com.example.suyeon.assignment0608.view.show
import kotlinx.android.synthetic.main.frag_add.*


/**
 * Created by SuYeon Park on 2020-06-09.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class AddFragment : Fragment() {

    private val TAG = "AddFragment"

    private lateinit var thread: NetWorkThread

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_create.setOnClickListener {
            createData()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        //null
        thread.cancel(true)
    }

    private fun createData() {
        thread = NetWorkThread(HttpMethod.POST, null, mapOf(
            "name" to et_name.text.toString(),
            "job" to et_job.text.toString()
        ), object : NetWorkThread.NetworkFinishListener {
            override fun onFinished(result: String) {
                Log.d(TAG, "result = " + result)
                context!!.show(result)
            }
        }
        )
        thread.execute()
    }

}