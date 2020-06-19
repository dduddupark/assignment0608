package com.example.suyeon.assignment0608.view.detail

import com.example.suyeon.assignment0608.data.Result


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface DetailInterface {

    interface View {
        fun infoResult(result: Result)
        fun editResult(result: Result)
    }

    interface Presenter {
        fun getUserInfo(id: String)
        fun editUserInfo(id: String, name: String)
    }

}