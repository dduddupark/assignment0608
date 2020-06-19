package com.example.suyeon.assignment0608.data


/**
 * Created by SuYeon Park on 2020-06-19.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
data class Result(var code: ResultCode, var data: Any?)

enum class ResultCode {
    SUCCESS, ERROR
}