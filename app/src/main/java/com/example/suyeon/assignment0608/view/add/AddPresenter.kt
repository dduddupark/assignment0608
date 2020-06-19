package com.example.suyeon.assignment0608.view.add

import com.example.suyeon.assignment0608.api.DefaultRepository
import com.example.suyeon.assignment0608.base.BasePresenter
import kotlinx.coroutines.launch


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class AddPresenter(val view: AddInterface.View) : AddInterface.Presenter, BasePresenter() {

    override fun createUser(name: String, job: String) {

        launch {
            view.createResult(DefaultRepository.createUser(name, job))
        }
    }
}