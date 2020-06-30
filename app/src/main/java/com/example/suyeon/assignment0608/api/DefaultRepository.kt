package com.example.suyeon.assignment0608.api

import com.example.suyeon.assignment0608.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

object DefaultRepository : NetworkRepository {

    private val TAG = "DefaultRepository"

    private val networkDispatcher = Dispatchers.IO

    override suspend fun getUserList(): Response<ArrayList<Employee>> =
        withContext(networkDispatcher) {

            val network = netWorkThread(HttpMethod.GET, null, null)
            if (ResultCode.SUCCESS == network.code) {
                Success(data = Employee.ParseArray.fromJson(network.data))
            } else {
                Error<ArrayList<Employee>>(exception = network.data)
            }
        }

    override suspend fun deleteUser(employee: Employee): Response<String> =
        withContext(networkDispatcher) {

            val network = netWorkThread(HttpMethod.DELETE, mapOf("id" to employee.id), null)

            if (ResultCode.SUCCESS == network.code) {
                Success(data = network.data)
            } else {
                Error<String>(exception = network.data)
            }
        }

    override suspend fun createUser(name: String, job: String): Response<String> =

        withContext(networkDispatcher) {

            val network = netWorkThread(HttpMethod.POST, null, mapOf("name" to name, "job" to job))

            if (ResultCode.SUCCESS == network.code) {
                Success(data = network.data)
            } else {
                Error<String>(exception = network.data)
            }
        }

    override suspend fun getUserInfo(id: String): Response<Employee?> =

        withContext(networkDispatcher) {

            val network = netWorkThread(HttpMethod.GET, mapOf(Param.ID to id), null)

            if (ResultCode.SUCCESS == network.code) {
                Success(data = Employee.ParseObject.fromJson(network.data))
            } else {
                Error<Employee>(exception = network.data)
            }
        }

    override suspend fun editUserInfo(id: String, name: String): Response<Person?> =

        withContext(networkDispatcher) {

            val network = netWorkThread(HttpMethod.PUT, mapOf("id" to id), mapOf("name" to name))

            if (ResultCode.SUCCESS == network.code) {
                Success(data = Person.ParseObject.fromJson(network.data))
            } else {
                Error<Person>(exception = network.data)
            }
        }
}