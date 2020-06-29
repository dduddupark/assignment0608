package com.example.suyeon.assignment0608.view.add


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface AddInterface {

    interface View {
        fun createResult(response: String?)
        fun error(error: String)
    }

    //여기로 들어와야됨
    interface Presenter {
        fun createUser(name: String, job: String)
    }

}