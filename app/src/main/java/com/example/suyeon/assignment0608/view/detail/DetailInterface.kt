package com.example.suyeon.assignment0608.view.detail

import com.example.suyeon.assignment0608.base.BaseInterface
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Person


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface DetailInterface {

    interface View : BaseInterface.View {
        fun infoResult(employee: Employee?)
        fun editResult(person: Person?)
    }

    interface Presenter {
        fun getUserInfo(id: String)
        fun editUserInfo(id: String, name: String)
    }

}