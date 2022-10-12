package com.example.ygodeckbuilder.di

import android.app.Application

class YGODeckBuilderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        YGODeckBuilderComponent =  DaggerYuGiOhComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
    companion object {
        lateinit var YGODeckBuilderComponent: YuGiOhComponent
    }
}
