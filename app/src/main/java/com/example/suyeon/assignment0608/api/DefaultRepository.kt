package com.example.suyeon.assignment0608.api

import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Param
import com.example.suyeon.assignment0608.data.Person
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

    override suspend fun getUserList(): ArrayList<Employee> {

        var list = ArrayList<Employee>()

        withContext(Dispatchers.IO) {

            val result = netWorkThread(HttpMethod.GET, null, null)

            result?.let {
                list = JsonParser.readEmployees(it)
            }
        }

        return list
    }

    override suspend fun deleteUser(employee: Employee): Boolean {

        var isSuccess = false

        withContext(Dispatchers.IO) {

            val result = netWorkThread(HttpMethod.DELETE, mapOf("id" to employee.id), null)

            result?.let {
                isSuccess = true
            }
        }

        return isSuccess
    }

    override suspend fun createUser(name: String, job: String): String {

        var response = ""

        withContext(Dispatchers.IO) {
            val result = netWorkThread(
                HttpMethod.POST, null, mapOf(
                    "name" to name,
                    "job" to job
                )
            )

            result?.let {
                response = result
            }
        }

        return response
    }

    override suspend fun getUserInfo(id: String): Employee? {

        var employee: Employee? = null

        withContext(Dispatchers.IO) {

            val result = netWorkThread(
                HttpMethod.GET,
                mapOf(Param.ID to id),
                null
            )

            result?.let {
                employee = JsonParser.readEmployee(result)
            }
        }

        return employee
    }

    override suspend fun editUserInfo(id: String, name: String): Person? {

        var response: Person? = null

        withContext(Dispatchers.IO) {
            val result = netWorkThread(
                HttpMethod.PUT,
                mapOf("id" to id),
                mapOf("name" to name)
            )

            result?.let {
                response = JsonParser.readPerson(result)
            }
        }

        return response
    }
}