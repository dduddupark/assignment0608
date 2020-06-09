package com.example.suyeon.assignment0608.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.api.HttpMethod
import com.example.suyeon.assignment0608.api.NetWorkThread
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.PARAM
import com.example.suyeon.assignment0608.setFragment
import com.example.suyeon.assignment0608.view.add.AddFragment
import com.example.suyeon.assignment0608.view.detail.DetailFragment
import kotlinx.android.synthetic.main.frag_main.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class MainFragment : Fragment() {

    private val TAG = "MainFragment"

    lateinit var thread: NetWorkThread

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onActivityCreated")

        btn_add.setOnClickListener {
            setFragment(
                R.id.fragment_container,
                AddFragment(),
                fragmentManager!!
            )
        }

        getData()
    }

    override fun onPause() {
        super.onPause()



        thread.cancel(true)

    }

    private fun getData() {

        thread = NetWorkThread(
            HttpMethod.GET, NetWorkThread.MAIN_URL, null,
            object : NetWorkThread.NetworkFinishListener {
                override fun onFinished(result: String) {
                    Log.d(TAG, "result = " + result)

                    val data = JSONObject(result).get("data")

                    if (data is JSONArray) {

                        val list = ArrayList<Employee>()

                        for (i in 0 until data.length()) {
                            val tempJson = data.getJSONObject(i)
                            list.add(
                                Employee(
                                    tempJson.getString(PARAM.ID),
                                    tempJson.getString(PARAM.EMAIL),
                                    tempJson.getString(PARAM.FIRST_NAME),
                                    tempJson.getString(PARAM.LAST_NAME),
                                    tempJson.getString(PARAM.AVARTAR)
                                )
                            )
                        }

                        Log.d(TAG, "list size = " + list.size)

                        if (list.isNotEmpty()) {
                            Log.d(TAG, "isNotEmpty")



                            setAdapter(list)
                        }
                    }
                }
            })

        thread.execute()
    }

    private fun setAdapter(list: ArrayList<Employee>) {
        rv_employee.adapter = MainAdapter(list,
            object :
                ItemClickListener {
                override fun onClick(employee: Employee) {
                    setFragment(
                        R.id.fragment_container,
                        DetailFragment.newInstance(employee.id),
                        fragmentManager!!
                    )
                }
            })
    }
}