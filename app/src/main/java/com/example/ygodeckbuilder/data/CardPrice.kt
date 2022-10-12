package com.example.ygodeckbuilder.data

import com.google.gson.annotations.SerializedName

data class CardPrice(
    @SerializedName("cardmarket_price")
    var cardMarketPrice : String? = null,
    @SerializedName("tcgplayer_price")
    var tcgPlayerPrice : String? = null,
    @SerializedName("ebay_price")
    var ebayPrice : String? = null,
    @SerializedName("amazon_price")
    var amazonPrice : String? = null,
    @SerializedName("coolstuffinc_price")
    var coolStuffIncPrice : String? = null
)
