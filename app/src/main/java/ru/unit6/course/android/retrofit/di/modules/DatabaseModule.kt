package ru.unit6.course.android.retrofit.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.unit6.course.android.retrofit.data.database.AppDatabase
import ru.unit6.course.android.retrofit.data.database.UserDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideUsersFromDatabase(database: AppDatabase): UserDao = database.userDao
}