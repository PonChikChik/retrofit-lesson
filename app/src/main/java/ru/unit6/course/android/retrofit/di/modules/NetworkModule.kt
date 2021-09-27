package ru.unit6.course.android.retrofit.di.modules

import dagger.Module
import dagger.Provides
import ru.unit6.course.android.retrofit.data.api.ApiService
import ru.unit6.course.android.retrofit.data.api.RetrofitBuilder
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService = RetrofitBuilder.apiService
}