package com.example.suyeon.assignment0608.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.data.*
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

    private val presenter: DetailInterface.Presenter = DetailPresenter(this)

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
            progress.visibility = View.VISIBLE
            presenter.editUserInfo(person_id.text.toString(), name.text.toString())
        }

        arguments?.getString(Param.ID)?.let {
            progress.visibility = View.VISIBLE
            presenter.getUserInfo(it)
        }
    }

    override fun infoResult(result: Result) {

        progress.visibility = View.GONE

        if (ResultCode.SUCCESS == result.code) {

            val employee = result.data as Employee

            employee.let {
                person_id.text = it.id
                email.text = it.email
                name.setText(it.firstName.plus(" ").plus(it.lastName))
                avatar.text = it.avatar
            }
        } else {
            context!!.show("정보를 가져오는데 실패했습니다. : ".plus(result.data))
            fragmentManager!!.popBackStack()
        }
    }

    override fun editResult(result: Result) {

        progress.visibility = View.GONE

        if (ResultCode.SUCCESS == result.code) {
            val person = result.data as Person?

            person?.let {
                name.setText(it.name)
                context!!.show("수정 성공")
            }
        } else {
            context!!.show("수정 실패 : ".plus(result.data))
        }
    }
}