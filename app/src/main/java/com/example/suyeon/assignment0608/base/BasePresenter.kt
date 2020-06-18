package com.example.suyeon.assignment0608.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


/**
 * Created by SuYeon Park on 2020-06-18.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
open class BasePresenter : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main
}