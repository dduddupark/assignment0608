package com.example.suyeon.assignment0608.api

import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Result


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface NetworkRepository {

    suspend fun getUserList(): Result

    suspend fun deleteUser(employee: Employee): Result

    suspend fun createUser(uname: String, job: String): Result

    suspend fun getUserInfo(id: String): Result

    suspend fun editUserInfo(id: String, name: String): Result

}