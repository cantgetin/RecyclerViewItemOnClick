package ru.unit6.course.android.retrofit.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
    suspend fun getUser(id: String) = apiService.getUser(id)
}