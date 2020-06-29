package com.example.suyeon.assignment0608.data


/**
 * Created by SuYeon Park on 2020-06-24.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

class Result(var code: ResultCode, var data: String)

open class Response<out T>(data: T? = null, exception: Exception? = null) {
    val data: T? = data
    val error: Exception? = exception
}

class Error<T>(
    val exception: Exception
) : Response<T>(null, exception)

class Success<T>(
    data: T
) : Response<T>(data)

enum class ResultCode {
    NONE, SUCCESS, ERROR
}