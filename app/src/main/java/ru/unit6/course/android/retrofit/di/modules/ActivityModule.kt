package ru.unit6.course.android.retrofit.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.unit6.course.android.retrofit.MainActivity

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}