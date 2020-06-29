package com.example.suyeon.assignment0608.view.add

import com.example.suyeon.assignment0608.base.BaseInterface


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface AddInterface {

    interface View : BaseInterface.View {
        fun createResult(response: String?)
    }

    //여기로 들어와야됨
    interface Presenter {
        fun createUser(name: String, job: String)
    }

}