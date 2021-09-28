package ru.unit6.course.android.retrofit.data.database

import androidx.room.*
import ru.unit6.course.android.retrofit.data.model.UserDB

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<UserDB>)

    @Update
    suspend fun updateUser(user: UserDB)

    @Delete
    suspend fun deleteUser(user: UserDB)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserDB>
}