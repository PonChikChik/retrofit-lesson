package ru.unit6.course.android.retrofit.data.repository

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import ru.unit6.course.android.retrofit.data.database.UserDao
import ru.unit6.course.android.retrofit.data.model.UserDB
import javax.inject.Inject

class LocalUsersRepository @Inject constructor(
    private val userDao: UserDao,
) {

    fun getAllLocalUsers() = userDao.getAllUsers()
        .subscribeOn(Schedulers.io())

    fun setUsersToDatabase(users: List<UserDB>) = Completable
        .fromRunnable { userDao.insertAllUsers(users) }
        .subscribeOn(Schedulers.io())
}