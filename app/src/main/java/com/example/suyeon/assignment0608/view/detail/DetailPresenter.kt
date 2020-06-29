package com.example.suyeon.assignment0608.view.detail

import com.example.suyeon.assignment0608.api.DefaultRepository
import com.example.suyeon.assignment0608.base.BasePresenter
import com.example.suyeon.assignment0608.data.Success
import kotlinx.coroutines.launch


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class DetailPresenter(val view: DetailInterface.View) : DetailInterface.Presenter, BasePresenter() {

    override fun getUserInfo(id: String) {

        view.loading(true)

        launch {

            val result = DefaultRepository.getUserInfo(id)

            if (result is Success) {
                view.infoResult(result.data)
            } else {
                view.error(result.error)
            }

            view.loading(false)
        }
    }

    override fun editUserInfo(id: String, name: String) {

        view.loading(true)

        launch {

            val result = DefaultRepository.editUserInfo(id, name)

            if (result is Success) {
                view.editResult(result.data)
            } else {
                view.error(result.error)
            }

            view.loading(false)
        }
    }
}