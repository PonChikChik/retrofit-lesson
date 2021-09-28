package ru.unit6.course.android.retrofit.di

import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.unit6.course.android.retrofit.MainApplication
import ru.unit6.course.android.retrofit.di.modules.*
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        AppModule::class,
        DatabaseModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setApplication(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(mainApplication: MainApplication)
}