package com.example.suyeon.assignment0608.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Param
import com.example.suyeon.assignment0608.data.Person
import com.example.suyeon.assignment0608.view.show
import kotlinx.android.synthetic.main.frag_detail.*


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
            person_id.text = it.id
            email.text = it.email
            name.setText(it.firstName.plus(" ").plus(it.lastName))
            avatar.text = it.avatar
        }
    }

    override fun editSuccess(person: Person?) {

        person?.let {
            name.setText(it.name)
            context!!.show("수정 성공")
        }
    }
}