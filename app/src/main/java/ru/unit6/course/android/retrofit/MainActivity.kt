package ru.unit6.course.android.retrofit

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ru.unit6.course.android.retrofit.data.database.AppDatabase
import ru.unit6.course.android.retrofit.di.FragmentFactory
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        AppDatabase.getInstance(applicationContext)
    }

    @Inject
    fun setFragmentFactory(fragmentFactory: FragmentFactory) {
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}