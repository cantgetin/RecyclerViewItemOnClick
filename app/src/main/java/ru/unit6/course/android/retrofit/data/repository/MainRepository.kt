package ru.unit6.course.android.retrofit.data.repository

import ru.unit6.course.android.retrofit.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
    suspend fun getUser(id: String) = apiHelper.getUser(id)

}