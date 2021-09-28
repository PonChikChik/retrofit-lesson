package ru.unit6.course.android.retrofit.di.modules

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.unit6.course.android.retrofit.di.FragmentKey
import ru.unit6.course.android.retrofit.ui.main.MainFragment

@Module
interface FragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    fun bindMainFragment(fragment: MainFragment): Fragment
}