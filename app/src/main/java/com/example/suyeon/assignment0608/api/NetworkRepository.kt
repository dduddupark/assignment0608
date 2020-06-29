package com.example.suyeon.assignment0608.api

import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Person
import com.example.suyeon.assignment0608.data.Response


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
interface NetworkRepository {

    suspend fun getUserList(): Response<ArrayList<Employee>>

    suspend fun deleteUser(employee: Employee): Response<String>

    suspend fun createUser(name: String, job: String): Response<String>

    suspend fun getUserInfo(id: String): Response<Employee?>

    suspend fun editUserInfo(id: String, name: String): Response<Person?>

}