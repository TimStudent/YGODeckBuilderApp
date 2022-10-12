package com.example.ygodeckbuilder.data

import com.google.gson.annotations.SerializedName

data class CardSet(
    @SerializedName("set_name")
    var setName : String? = null,
    @SerializedName("set_code")
    var setCode : String? = null,
    @SerializedName("set_rarity")
    var setRarity : String? = null,
    @SerializedName("set_rarity_code")
    var setRarityCode : String? = null,
    @SerializedName("set_price")
    var setPrice : String? = null
)
