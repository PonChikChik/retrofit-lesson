package ru.unit6.course.android.retrofit.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.unit6.course.android.retrofit.data.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        AppDatabase.getInstance(context)
}