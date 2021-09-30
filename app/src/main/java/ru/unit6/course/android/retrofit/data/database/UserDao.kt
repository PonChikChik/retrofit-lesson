package ru.unit6.course.android.retrofit.data.database

import androidx.room.*
import io.reactivex.Single
import ru.unit6.course.android.retrofit.data.model.UserDB

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(users: List<UserDB>)

    @Update
    fun updateUser(user: UserDB)

    @Delete
    fun deleteUser(user: UserDB)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<UserDB>>
}