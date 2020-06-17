package com.example.suyeon.assignment0608.view.detail

import com.example.suyeon.assignment0608.api.DefaultRepository
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
class DetailPresenter(val view: DetailInterface.View): DetailInterface.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun getUserInfo(id: String) {

        launch {
            view.setInfo(DefaultRepository.getUserInfo(id))
        }

    }

    override fun editUserInfo(id: String, name: String) {
        launch {
            view.editSuccess(DefaultRepository.editUserInfo(id, name))
        }
    }
}