package com.example.suyeon.assignment0608.api

import android.util.Log
import com.example.suyeon.assignment0608.data.Employee
import com.example.suyeon.assignment0608.data.Param
import com.example.suyeon.assignment0608.data.Person
import org.json.JSONArray
import org.json.JSONObject


/**
 * Created by SuYeon Park on 2020-06-18.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

object JsonParser {

    fun readEmployees(result: String?): ArrayList<Employee> {

        val list = ArrayList<Employee>()

        if (result != null) {
            val data = JSONObject(result)

            try {

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

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return list
    }

    fun readEmployee(result: String): Employee? {

        var employee: Employee? = null

        val data = JSONObject(result)

        try {

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

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return employee
    }

    fun readPerson(result: String): Person? {

        Log.d("JsonParser", result)

        var person: Person? = null

        try {

            val data = JSONObject(result)
            person = Person(data.getString(Param.NAME) ?: "")


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return person
    }

}