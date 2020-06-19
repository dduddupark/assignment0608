package com.example.suyeon.assignment0608.api

import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Param
import com.example.suyeon.assignment0608.data.Result
import com.example.suyeon.assignment0608.data.ResultCode
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
    private val repositoryError = "repositoryError : cannot draw close Dispatchers.IO"

    override suspend fun getUserList(): Result {

        var result = Result(ResultCode.ERROR, repositoryError)

        withContext(Dispatchers.IO) {
            result = netWorkThread(HttpMethod.GET, null, null)

            if (ResultCode.SUCCESS == result.code) {
                result.data = JsonParser.readEmployees(result.data as String)
            }
        }

        return result
    }

    override suspend fun deleteUser(employee: Employee): Result {

        var result = Result(ResultCode.ERROR, repositoryError)

        withContext(Dispatchers.IO) {
            result = netWorkThread(HttpMethod.DELETE, mapOf("id" to employee.id), null)
        }

        return result
    }

    override suspend fun createUser(name: String, job: String): Result {

        var result = Result(ResultCode.ERROR, repositoryError)

        withContext(Dispatchers.IO) {
            result = netWorkThread(
                HttpMethod.POST, null, mapOf(
                    "name" to name,
                    "job" to job
                )
            )
        }

        return result
    }

    override suspend fun getUserInfo(id: String): Result {

        var result = Result(ResultCode.ERROR, repositoryError)

        withContext(Dispatchers.IO) {

            result = netWorkThread(
                HttpMethod.GET,
                mapOf(Param.ID to id),
                null
            )

            if (ResultCode.SUCCESS == result.code) {
                result.data = JsonParser.readEmployee(result.data as String)
            }

        }

        return result
    }

    override suspend fun editUserInfo(id: String, name: String): Result {

        var result = Result(ResultCode.ERROR, repositoryError)

        withContext(Dispatchers.IO) {
            result = netWorkThread(
                HttpMethod.PUT,
                mapOf("id" to id),
                mapOf("name" to name)
            )

            if (ResultCode.SUCCESS == result.code) {
                result.data = JsonParser.readPerson(result.data as String)
            }
        }

        return result
    }
}