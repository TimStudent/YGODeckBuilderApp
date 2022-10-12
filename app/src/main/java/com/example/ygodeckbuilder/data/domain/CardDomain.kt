package com.example.ygodeckbuilder.data.domain

import com.example.ygodeckbuilder.data.CardData

data class CardDomain(
    val id: Int? = 0,
    val name: String? = "Test Card",
    val type: String? = "Test Type",
    val desc: String? = "Test Desc",
    val race: String? = "Test Race",
    val archetype: String? = "Test Archetype",
    val setName : String? = "Test Name of Set",
    val setCode: String? = "Test Set Code",
    val setRarity: String? = "Test Rarity",
    val setRarityCode: String? = "Test Rarity Code",
    val setPrice: String? = "Test Set Price",
    val imageURL: String? = "https://images.ygoprodeck.com/images/cards/14558127.jpg",
    val imageURLSmall: String? = null,
    val cardMarketPrice: String? = "test card market price",
    val tcgPlayerPrice: String? = "test tcg player price",
    val ebayPrice: String? = "test ebay price",
    val amazonPrice: String? = "test amazon price",
    val coolStuffIncPrice: String? = "test cool stuff inc price"
)

fun List<CardData?>?.mapToDomainCards(): List<CardDomain>? {
    return this?.map { card ->
        CardDomain(
            id = (card?.id ?: "") as Int,
            name = card?.name ?: "Invalid Card Name",
            type = card?.type ?: "Invalid Card Type",
            desc = card?.desc ?: "Invalid Card Description",
            race = card?.race ?: "Invalid Card Race",
            archetype = card?.archetype ?: "Invalid Card Archetype",
            setName = card?.cardSet?.firstOrNull()?.setName ?: "Invalid Card Set Name",
            setCode = card?.cardSet?.firstOrNull()?.setCode ?: "Invalid Set Code",
            setRarity = card?.cardSet?.firstOrNull()?.setRarity ?: "Invalid Set Rarity",
            setRarityCode = card?.cardSet?.first()?.setRarityCode ?: "Invalid Set Rarity Code",
            setPrice = card?.cardSet?.first()?.setPrice ?: "Invalid Set Price",
            imageURL = card?.cardImages?.firstOrNull()?.imageUrl ?: "Invalid Image URL",
            imageURLSmall = card?.cardImages?.firstOrNull()?.imageUrlSmall ?: "Invalid Image URL",
            cardMarketPrice = card?.cardPrices?.firstOrNull()?.cardMarketPrice ?: "Invalid Card Market Price",
            tcgPlayerPrice = card?.cardPrices?.firstOrNull()?.tcgPlayerPrice ?: "Invalid TCGPlayer Price",
            ebayPrice = card?.cardPrices?.firstOrNull()?.ebayPrice ?: "Invalid Ebay price",
            amazonPrice = card?.cardPrices?.firstOrNull()?.amazonPrice ?: "Invalid Amazon Price",
            coolStuffIncPrice = card?.cardPrices?.firstOrNull()?.coolStuffIncPrice ?: "Invalid CoolStuffInc Price"
        )
    } ?: emptyList()
}
