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

    override suspend fun getUserList(): Response<ArrayList<Employee>> = withContext(Dispatchers.IO) {

        val network = netWorkThread(HttpMethod.GET, null, null)
        if (network.code == ResultCode.SUCCESS) {

            Success(data = Employee.parseArray.fromJson(network.data))
        } else {
            Error(exception = Exception("error"), data = ArrayList<Employee>())
        }

    }


    override suspend fun deleteUser(employee: Employee): Response<String> {

        val response = Response<String>()
        response.code = ResultCode.ERROR

        withContext(Dispatchers.IO) {

            val network = netWorkThread(HttpMethod.DELETE, mapOf("id" to employee.id), null)

            response.code = network.code
            response.data = network.data
        }

        return response
    }

    override suspend fun createUser(name: String, job: String): Response<String> {

        val response = Response<String>()
        response.code = ResultCode.ERROR

        withContext(Dispatchers.IO) {

            val network = netWorkThread(
                HttpMethod.POST, null, mapOf(
                    "name" to name,
                    "job" to job
                )
            )


            response.code = network.code
            response.data = network.data
        }

        return response
    }

    override suspend fun getUserInfo(id: String): Response<Employee> {

        val response = Response<Employee>()
        response.code = ResultCode.ERROR

        withContext(Dispatchers.IO) {

            val network = netWorkThread(
                HttpMethod.GET,
                mapOf(Param.ID to id),
                null
            )

            response.code = network.code

            if (network.code == ResultCode.SUCCESS) {
                val json = Employee.Companion.parseObject.fromJson(network.data.toString())
                response.data = json
            } else if (network.code == ResultCode.ERROR) {
                response.data = null
            }
        }

        return response
    }

    override suspend fun editUserInfo(id: String, name: String): Response<Person> {

        val response = Response<Person>()
        response.code = ResultCode.ERROR

        withContext(Dispatchers.IO) {

            val network = netWorkThread(
                HttpMethod.PUT,
                mapOf("id" to id),
                mapOf("name" to name)
            )

            response.code = network.code

            if (network.code == ResultCode.SUCCESS) {
                val json = Person.fromJson(network.data)
                response.data = json
            } else if (network.code == ResultCode.ERROR) {
                response.data = null
            }
        }

        return response
    }
}