package com.example.suyeon.assignment0608.base


/**
 * Created by SuYeon Park on 2020-06-29.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

interface BaseInterface {
    interface View {
        fun error(error: String)
        fun loading(isShow: Boolean)
    }
}