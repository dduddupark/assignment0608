package com.example.suyeon.assignment0608.data


/**
 * Created by SuYeon Park on 2020-06-24.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

class Result(var code: ResultCode, var data: String)

open class Response<out T>(val data: T? = null, val error: String = "")

class Error<T>(
    exception: String
) : Response<T>(error = exception)

class Success<T>(
    data: T
) : Response<T>(data = data)

enum class ResultCode {
    SUCCESS, ERROR
}