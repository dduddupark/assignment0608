package com.example.suyeon.assignment0608.api

import android.util.Log
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Param
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject


/**
 * Created by SuYeon Park on 2020-06-16.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
object DefaultRepository: NetworkRepository {

    private val TAG = "DefaultRepository"

    override suspend fun getUserList(): List<Employee> {

        var list = ArrayList<Employee>()

        withContext(Dispatchers.IO) {

           val result = netWorkThread(HttpMethod.GET, null, null)

            result?.let {

                try {

                    val data = JSONObject(result)

                    if(data is JSONObject) {
                        val json = data.get("data")

                        if (json is JSONArray) {

                            for (i in 0 until json.length()) {
                                val tempJson = json.getJSONObject(i)
                                list.add(
                                    Employee(
                                        tempJson.getString(Param.ID),
                                        tempJson.getString(Param.EMAIL),
                                        tempJson.getString(Param.FIRST_NAME),
                                        tempJson.getString(Param.LAST_NAME),
                                        tempJson.getString(Param.AVATAR)
                                    )
                                )
                            }
                        }

                        Log.d(TAG, "parsing end")
                    }

                } catch (e: Exception) {

                }
            }
        }

        Log.d(TAG, "list = " + list)

        return list
    }

    override suspend fun deleteUser(employee: Employee): Boolean {

        var isSuccess = false

       /* withContext(Dispatchers.IO) {
            NetWorkThread(HttpMethod.DELETE, mapOf("id" to employee.id), null,
                object : NetWorkThread.NetworkFinishListener {
                    override fun onFinished(result: String) {
                        isSuccess = true
                    }
                }
            ).execute()
        }*/

        return isSuccess
    }

    override suspend fun createUser(name: String, job: String): String {

        var response = ""

        /*NetWorkThread(HttpMethod.POST, null, mapOf(
            "name" to name,
            "job" to job
        ), object : NetWorkThread.NetworkFinishListener {
            override fun onFinished(result: String) {
                response = result
            }
        }).execute()*/

        return response
    }

    override suspend fun getUserInfo(id: String): Employee? {

        var employee: Employee? = null

        /*NetWorkThread(
            HttpMethod.GET,
            mapOf(Param.ID to id),
            null,
            object : NetWorkThread.NetworkFinishListener {
                override fun onFinished(result: String) {

                    //json parser 만들기
                    val data = JSONObject(result).getJSONObject("data")

                    employee = Employee(
                            data.getString(Param.ID),
                            data.getString(Param.EMAIL),
                            data.getString(Param.FIRST_NAME),
                            data.getString(Param.LAST_NAME),
                            data.getString(Param.AVATAR)
                        )
                }
            }
        ).execute()*/

        return employee
    }

    override suspend fun editUserInfo(id: String, name: String): String {

        var response = ""

      /*  NetWorkThread(
            HttpMethod.PUT,
            mapOf("id" to id),
            mapOf("name" to name),
            object : NetWorkThread.NetworkFinishListener {
                override fun onFinished(result: String) {
                    response = result
                }
            }

        ).execute()*/

        return response
    }
}