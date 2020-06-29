package com.example.suyeon.assignment0608.view.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.view.show
import kotlinx.android.synthetic.main.frag_add.*


/**
 * Created by SuYeon Park on 2020-06-09.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class AddFragment : Fragment(), AddInterface.View {

    private val TAG = "AddFragment"

    private val presenter: AddInterface.Presenter = AddPresenter(this)

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
            progress.visibility = View.VISIBLE
            presenter.createUser(et_name.text.toString(), et_job.text.toString())
        }
    }

    override fun createResult(response: String?) {
        if (response == null) {
            context!!.show("생성 실패")
        } else {
            context!!.show("생성 성공")
        }
    }

    override fun error(error: String) {
        context!!.show(error)
    }

    override fun loading(isShow: Boolean) {
        progress?.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}