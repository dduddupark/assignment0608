package com.example.suyeon.assignment0608.data


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
)

object PARAM {
    const val ID = "id"
    const val EMAIL = "email"
    const val FIRST_NAME = "first_name"
    const val LAST_NAME = "last_name"
    const val AVATAR = "avatar"
}