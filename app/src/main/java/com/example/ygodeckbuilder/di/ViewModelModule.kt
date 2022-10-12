package com.example.ygodeckbuilder.di

import androidx.lifecycle.ViewModelProvider
import com.example.ygodeckbuilder.rest.YGODeckBuilderRepository
import com.example.ygodeckbuilder.utils.YGODeckBuilderModelFactory

import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideViewModelFactory(
        repository: YGODeckBuilderRepository
    ): YGODeckBuilderModelFactory =
        YGODeckBuilderModelFactory(repository)
}