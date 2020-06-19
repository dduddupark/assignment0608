package com.example.suyeon.assignment0608.view.main

import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Result

/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface MainInterface {

    interface View {
        fun listResult(result: Result)
        fun deleteResult(result: Result)
    }

    interface Presenter {
        fun getUserList()
        fun deleteUser(employee: Employee)
    }

}