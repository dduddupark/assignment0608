package com.example.suyeon.assignment0608.view.main

import com.example.suyeon.assignment0608.data.Employee


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface MainInterface {

    interface View {
        fun showList(list: ArrayList<Employee>)
        fun deleteSuccess(isSuccess: Boolean)
    }

    interface Presenter {
        fun getUserList()
        fun deleteUser(employee: Employee)
    }

}