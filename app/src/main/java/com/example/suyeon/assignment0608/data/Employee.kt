package com.example.suyeon.assignment0608.data

import com.example.suyeon.assignment0608.api.JsonFactory
import org.json.JSONArray
import org.json.JSONObject


/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
data class Employee(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
) {

    //Companion Object(= static final) 필요없음

    object ParseObject : JsonFactory<Employee> {
        override fun fromJson(jsonResult: String): Employee? {
            var employee: Employee? = null

            val data = JSONObject(jsonResult)

            if (data.has("data")) {

                val json = data.get("data")

                if (json is JSONObject) {
                    employee = Employee(
                        json.getString(Param.ID) ?: "",
                        json.getString(Param.EMAIL) ?: "",
                        json.getString(Param.FIRST_NAME) ?: "",
                        json.getString(Param.LAST_NAME) ?: "",
                        json.getString(Param.AVATAR) ?: ""
                    )
                }

            }

            return employee
        }
    }

    object ParseArray : JsonFactory<ArrayList<Employee>> {
        override fun fromJson(jsonResult: String): ArrayList<Employee> {
            val list = ArrayList<Employee>()

            val data = JSONObject(jsonResult)

            if (data.has("data")) {
                val json = data.get("data")

                if (json is JSONArray) {

                    for (i in 0 until json.length()) {
                        val tempJson = json.getJSONObject(i)
                        list.add(
                            Employee(
                                tempJson.getString(Param.ID) ?: "",
                                tempJson.getString(Param.EMAIL) ?: "",
                                tempJson.getString(Param.FIRST_NAME) ?: "",
                                tempJson.getString(Param.LAST_NAME) ?: "",
                                tempJson.getString(Param.AVATAR) ?: ""
                            )
                        )
                    }
                }
            }

            /*try {   //메모리를 많이 씀
                    //IO Exception, NumberFormat Exception 같은 못잡는것만 잡고 웬만하면 쓰지말기

            } catch (e: Exception) {
                e.printStackTrace()
            }*/

            return list
        }
    }

}

