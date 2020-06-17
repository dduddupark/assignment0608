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
        fun successCreateUser(result: String)
    }

    interface Presenter {
        fun createUser(name: String, job: String)
    }

}