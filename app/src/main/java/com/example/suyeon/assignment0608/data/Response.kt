package com.example.suyeon.assignment0608.data


/**
 * Created by SuYeon Park on 2020-06-24.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

class Result(var code: ResultCode, var data: String)

class Response<T> {
    var code: ResultCode = ResultCode.NONE
    var data: T? = null
}


enum class ResultCode {
    NONE, SUCCESS, ERROR
}