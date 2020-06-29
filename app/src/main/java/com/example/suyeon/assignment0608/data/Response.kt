package com.example.suyeon.assignment0608.data


/**
 * Created by SuYeon Park on 2020-06-24.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

class Result(var code: ResultCode, var data: String)

open class Response<out T>(data: T? = null, exception: String = "") {
    val data: T? = data
    val error: String = exception
}

class Error<T>(
    val exception: String
) : Response<T>(exception = exception)

class Success<T>(
    data: T
) : Response<T>(data = data)

enum class ResultCode {
    SUCCESS, ERROR
}