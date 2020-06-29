package com.example.suyeon.assignment0608.view.main

import com.example.suyeon.assignment0608.base.BaseInterface
import com.example.suyeon.assignment0608.data.Employee

/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface MainInterface {

    interface View : BaseInterface.View {
        fun listResult(list: ArrayList<Employee>?)
        fun deleteResult(response: String?)
    }

    interface Presenter {
        fun getUserList()
        fun deleteUser(employee: Employee)
    }

}