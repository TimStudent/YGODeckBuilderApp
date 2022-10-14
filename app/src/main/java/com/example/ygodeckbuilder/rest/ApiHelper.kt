package com.example.ygodeckbuilder.rest

import com.example.ygodeckbuilder.data.Card
import com.example.ygodeckbuilder.di.NetworkModule
import retrofit2.Response
import retrofit2.http.GET

interface ApiHelper {

    @GET(PATH_SEARCH)
    //this should grab all cards
    suspend fun getCards(

    ): Response<Card>

    companion object {
        const val BASE_URL = "https://db.ygoprodeck.com/api/v7/"
        private const val PATH_SEARCH = "cardinfo.php"
        val serviceApi: ApiHelper = ApiService.cardService

    }
}