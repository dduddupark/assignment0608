package com.example.suyeon.assignment0608.view.detail

import com.example.suyeon.assignment0608.api.DefaultRepository
import com.example.suyeon.assignment0608.base.BasePresenter
import com.example.suyeon.assignment0608.data.ResultCode
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

            val result = DefaultRepository.getUserInfo(id)

            if (result.code == ResultCode.SUCCESS) {
                view.infoResult(result.data)
            } else {
                view.error()
            }
        }

    }

    override fun editUserInfo(id: String, name: String) {
        launch {

            val response = DefaultRepository.editUserInfo(id, name)

            if (response.code == ResultCode.SUCCESS) {
                response.data?.let { view.editResult(it) }
            } else {
                view.error()
            }

        }
    }
}