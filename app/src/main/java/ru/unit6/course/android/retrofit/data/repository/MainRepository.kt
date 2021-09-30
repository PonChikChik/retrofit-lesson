package ru.unit6.course.android.retrofit.data.repository

import io.reactivex.Single
import ru.unit6.course.android.retrofit.data.api.ApiService
import ru.unit6.course.android.retrofit.data.model.User
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getUsers(): Single<List<User>> = apiService.getUsers()
}