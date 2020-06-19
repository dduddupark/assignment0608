package com.example.suyeon.assignment0608.view.detail

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
class DetailPresenter(val view: DetailInterface.View) : DetailInterface.Presenter, BasePresenter() {

    override fun getUserInfo(id: String) {

        launch {
            view.infoResult(DefaultRepository.getUserInfo(id))
        }

    }

    override fun editUserInfo(id: String, name: String) {
        launch {
            view.editResult(DefaultRepository.editUserInfo(id, name))
        }
    }
}