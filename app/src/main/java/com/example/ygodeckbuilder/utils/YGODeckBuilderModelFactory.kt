package com.example.ygodeckbuilder.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ygodeckbuilder.rest.YGODeckBuilderRepository
import com.example.ygodeckbuilder.viewmodel.CardViewModel

class YGODeckBuilderModelFactory(
    private val repository: YGODeckBuilderRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardViewModel(repository) as T
    }
}
