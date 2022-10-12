package com.example.ygodeckbuilder.utils

import com.example.ygodeckbuilder.data.domain.CardDomain

sealed class UIState {
    object LOADING: UIState()
    data class SUCCESS(
        val success: List<CardDomain>? = null
    ) : UIState()
    data class ERROR(val error:Exception) : UIState()
}
