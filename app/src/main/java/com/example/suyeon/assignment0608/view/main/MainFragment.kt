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
import com.example.suyeon.assignment0608.view.add.AddFragment
import com.example.suyeon.assignment0608.view.detail.DetailFragment
import com.example.suyeon.assignment0608.view.set
import com.example.suyeon.assignment0608.view.show
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

    private lateinit var thread: NetWorkThread

    private lateinit var adapter: MainAdapter

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
            fragmentManager!!.set(
                R.id.fragment_container,
                AddFragment()
            )
        }

        adapter = MainAdapter(
            object :
                ItemClickListener {
                override fun onClick(employee: Employee) {
                    fragmentManager!!.set(
                        R.id.fragment_container,
                        DetailFragment.newInstance(employee.id)
                    )
                }

                override fun onDelete(employee: Employee) {
                    deleteData(employee)
                }
            })

        rv_employee.adapter = adapter

        getListData()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        thread.cancel(true)
    }

    private fun getListData() {

        thread = NetWorkThread(
            HttpMethod.GET, null, null,
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
                                    tempJson.getString(PARAM.AVATAR)
                                )
                            )
                        }

                        Log.d(TAG, "list size = " + list.size)

                        if (list.isNotEmpty()) {
                            setList(list)
                        }
                    }
                }
            })

        thread.execute()
    }

    private fun deleteData(employee: Employee) {
        thread = NetWorkThread(HttpMethod.DELETE, mapOf("id" to employee.id), null,
            object : NetWorkThread.NetworkFinishListener {
                override fun onFinished(result: String) {
                    Log.d(TAG, "result = " + result)

                    context!!.show("삭제성공")
                    getListData()
                }
            }
        )

        thread.execute()
    }

    private fun setList(list: ArrayList<Employee>) {
        adapter.setData(list)
        context!!.show("데이터를 불러왔습니다.")
    }
}