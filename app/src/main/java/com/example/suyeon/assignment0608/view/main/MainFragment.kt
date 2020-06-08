package com.example.suyeon.assignment0608.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.api.NetWorkThread
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.setFragment
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "onActivityCreated")

        getData()
    }

    private fun getData() {

        NetWorkThread("GET", "employees", null,
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
                                    tempJson.getString("id"),
                                    tempJson.getString("employee_name"),
                                    tempJson.getString("employee_salary"),
                                    tempJson.getString("employee_age")
                                )
                            )
                        }

                        Log.d(TAG, "list size = " + list.size)

                        if (list.isNotEmpty()) {
                            Log.d(TAG, "isNotEmpty")
                            rv_employee.adapter =
                                MainAdapter(
                                    list,
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
                }
            }).execute()
    }
}