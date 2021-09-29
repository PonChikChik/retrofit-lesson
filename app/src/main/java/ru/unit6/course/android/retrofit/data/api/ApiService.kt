package ru.unit6.course.android.retrofit.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.unit6.course.android.retrofit.data.model.User

interface ApiService {

    @GET("users")
    fun getUsers(): Single<List<User>>

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: String): Single<User>

}