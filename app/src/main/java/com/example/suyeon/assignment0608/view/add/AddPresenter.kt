package com.example.suyeon.assignment0608.view.add

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
class AddPresenter(val view: AddInterface.View): AddInterface.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun createUser(name: String, job: String) {

        launch {
             view.successCreateUser(DefaultRepository.createUser(name, job))
        }
    }
}