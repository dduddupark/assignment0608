package com.example.suyeon.assignment0608.api


/**
 * Created by SuYeon Park on 2020-06-24.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface JsonFactory<T> {
    fun fromJson(jsonResult: String): T?
}