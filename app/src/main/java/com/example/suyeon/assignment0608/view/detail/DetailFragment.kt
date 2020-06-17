package com.example.suyeon.assignment0608.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Param
import com.example.suyeon.assignment0608.view.show
import kotlinx.android.synthetic.main.frag_detail.*
import org.json.JSONObject


/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class DetailFragment : Fragment(), DetailInterface.View {

    private val TAG = "DetailFragment"

    private val presenter = DetailPresenter(this)

    companion object {
        fun newInstance(id: String): DetailFragment {
            return DetailFragment().apply {
                val bundle = Bundle()
                bundle.putString(Param.ID, id)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit.setOnClickListener {
            presenter.editUserInfo(person_id.text.toString(), name.text.toString())
        }

        arguments?.getString(Param.ID)?.let {
            presenter.getUserInfo(it)
        }
    }

    override fun setInfo(employee: Employee?) {

        employee?.let {
            person_id.text = employee.id
            email.text = employee.email
            name.setText(employee.firstName.plus(" ").plus(employee.lastName))
            avatar.text = employee.avatar
        }
    }

    override fun editSuccess(result: String) {
        name.setText(JSONObject(result).getString("name"))
        context!!.show(result)
    }
}