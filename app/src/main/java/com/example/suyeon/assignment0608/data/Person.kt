package com.example.suyeon.assignment0608.data

import com.example.suyeon.assignment0608.api.JsonFactory
import org.json.JSONObject


/**
 * Created by SuYeon Park on 2020-06-18.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
data class Person(
    val name: String
) {
    companion object : JsonFactory<Person> {
        override fun fromJson(jsonResult: String): Person? {
            var person: Person? = null

            try {

                val data = JSONObject(jsonResult)
                person = Person(data.getString(Param.NAME) ?: "")


            } catch (e: Exception) {
                e.printStackTrace()
            }

            return person
        }
    }

}

