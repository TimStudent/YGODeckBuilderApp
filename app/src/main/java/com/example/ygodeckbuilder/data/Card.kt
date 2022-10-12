package com.example.ygodeckbuilder.data

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("data")
    val cards: List<CardData?>? = null
)