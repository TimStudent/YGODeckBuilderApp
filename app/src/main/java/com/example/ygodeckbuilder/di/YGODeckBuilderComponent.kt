package com.example.ygodeckbuilder.di

import com.example.ygodeckbuilder.MainActivity2
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface YuGiOhComponent {
    fun inject(mainActivity: MainActivity2)
}