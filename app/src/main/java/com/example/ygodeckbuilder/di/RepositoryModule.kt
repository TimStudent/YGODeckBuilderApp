package com.example.ygodeckbuilder.di

import com.example.ygodeckbuilder.rest.YGODeckBuilderRepository
import com.example.ygodeckbuilder.rest.YGODeckBuilderRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesYGODeckBuilderRepository(
        impl: YGODeckBuilderRepositoryImpl
    ): YGODeckBuilderRepository
}