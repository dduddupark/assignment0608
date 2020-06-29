package com.example.suyeon.assignment0608.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.view.add.AddFragment
import com.example.suyeon.assignment0608.view.detail.DetailFragment
import com.example.suyeon.assignment0608.view.set
import com.example.suyeon.assignment0608.view.show
import kotlinx.android.synthetic.main.frag_main.*

/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class MainFragment : Fragment(), MainInterface.View {

    private val TAG = "MainFragment"

    private lateinit var adapter: MainAdapter

    private val presenter: MainInterface.Presenter = MainPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated")

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

                    progress.visibility = View.VISIBLE
                    presenter.deleteUser(employee)
                }
            })

        rv_employee.adapter = adapter


        progress.visibility = View.VISIBLE
        presenter.getUserList()
    }

    override fun listResult(list: ArrayList<Employee>?) {
        progress?.visibility = View.GONE

        if (list != null) {
            tv_error?.visibility = View.GONE
            adapter.setData(list)
            context!!.show("데이터를 불러왔습니다.")
        } else {
            tv_error?.visibility = View.VISIBLE
            tv_error?.text = "데이터를 불러오는데 실패했습니다."
        }

    }

    override fun deleteResult(response: String?) {

        progress?.visibility = View.GONE

        if (response != null) {
            context!!.show("삭제 성공")
            presenter.getUserList()
        } else {
            context!!.show("삭제 실패")
        }
    }

    override fun error(error: String) {
        progress?.visibility = View.GONE
        context!!.show(error)
    }
}