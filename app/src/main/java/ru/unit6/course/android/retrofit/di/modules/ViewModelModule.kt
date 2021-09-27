package ru.unit6.course.android.retrofit.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.unit6.course.android.retrofit.di.ViewModelKey
import ru.unit6.course.android.retrofit.ui.main.MainViewModel

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}