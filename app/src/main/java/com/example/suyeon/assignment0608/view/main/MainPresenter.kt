package com.example.suyeon.assignment0608.view.main

import com.example.suyeon.assignment0608.api.DefaultRepository
import com.example.suyeon.assignment0608.base.BasePresenter
import com.example.suyeon.assignment0608.data.Employee
import kotlinx.coroutines.launch


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class MainPresenter(val view: MainInterface.View) : MainInterface.Presenter, BasePresenter() {

    override fun getUserList() {
        launch {
            view.showList(DefaultRepository.getUserList())
        }
    }

    override fun deleteUser(employee: Employee) {
        launch {
            view.deleteSuccess(DefaultRepository.deleteUser(employee))
        }

    }
}