package com.example.suyeon.assignment0608.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.api.HttpMethod
import com.example.suyeon.assignment0608.api.NetWorkThread
import com.example.suyeon.assignment0608.api.NetWorkThread.Companion.DETAIL_URL
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.PARAM
import kotlinx.android.synthetic.main.frag_detail.*

import org.json.JSONObject


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
                bundle.putString(PARAM.ID, id)

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

        arguments?.getString(PARAM.ID)?.let {
            getData(it)
        }
    }

    private fun getData(param: String) {
        NetWorkThread(
            HttpMethod.GET, DETAIL_URL, mapOf(PARAM.ID to param),
            object : NetWorkThread.NetworkFinishListener {
                override fun onFinished(result: String) {
                    Log.d(TAG, "result = " + result)

                    val data = JSONObject(result).getJSONObject("data")

                    setText(
                        Employee(
                            data.getString(PARAM.ID),
                            data.getString(PARAM.EMAIL),
                            data.getString(PARAM.FIRST_NAME),
                            data.getString(PARAM.LAST_NAME),
                            data.getString(PARAM.AVARTAR)
                        )
                    )
                }
            }
        ).execute()
    }

    private fun setText(employee: Employee) {
        person_id.text = employee.id
        email.text = employee.email
        first_name.text = employee.firstName
        last_name.text = employee.lastName
        avatar.text = employee.avatar
    }
}