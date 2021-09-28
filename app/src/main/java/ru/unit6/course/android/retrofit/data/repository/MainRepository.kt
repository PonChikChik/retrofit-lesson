package ru.unit6.course.android.retrofit.data.repository

import ru.unit6.course.android.retrofit.data.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUsers() = apiService.getUsers()
}