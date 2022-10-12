package com.example.ygodeckbuilder.data

import com.google.gson.annotations.SerializedName

data class CardData(
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("type")
    var type : String? = null,
    @SerializedName("desc")
    var desc : String? = null,
    @SerializedName("race")
    var race : String? = null,
    @SerializedName("archetype")
    var archetype : String? = null,
    @SerializedName("card_sets")
    var cardSet : List<CardSet?>? = null,
    @SerializedName("card_images")
    var cardImages : List<CardImage?>? = null,
    @SerializedName("card_prices")
    var cardPrices : List<CardPrice?>? = null
)
