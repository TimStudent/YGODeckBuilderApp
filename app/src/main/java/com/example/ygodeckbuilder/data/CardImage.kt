package com.example.ygodeckbuilder.data

import com.google.gson.annotations.SerializedName

data class CardImage(
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("image_url")
    var imageUrl : String? = null,
    @SerializedName("image_url_small")
    var imageUrlSmall : String? = null
)
