package com.example.suyeon.assignment0608.view.main

import com.example.suyeon.assignment0608.api.DefaultRepository
import com.example.suyeon.assignment0608.data.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class MainPresenter(val view: MainInterface.View): MainInterface.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun getUserList() {

        launch {
            DefaultRepository.getUserList().run {

                launch(Dispatchers.Main) {
                    view.showList(this as ArrayList<Employee>)
                }
            }
        }
    }

    override fun deleteUser(employee: Employee) {

        launch {
            view.deleteSuccess(DefaultRepository.deleteUser(employee))
        }

    }
}