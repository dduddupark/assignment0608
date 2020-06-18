package com.example.suyeon.assignment0608.api

import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Person


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface NetworkRepository {

    suspend fun getUserList(): List<Employee>

    suspend fun deleteUser(employee: Employee): Boolean

    suspend fun createUser(uname: String, job: String): String

    suspend fun getUserInfo(id: String): Employee?

    suspend fun editUserInfo(id: String, name: String): Person?

}