package com.example.ygodeckbuilder.rest

import com.example.ygodeckbuilder.data.domain.mapToDomainCards
import com.example.ygodeckbuilder.utils.CardType
import com.example.ygodeckbuilder.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface YGODeckBuilderRepository {
    fun getAllCards(): Flow<UIState>
    fun getCardsByType(cardType: CardType): Flow<UIState>
    fun getCardsByName(cardName: String): Flow<UIState>
}

class YGODeckBuilderRepositoryImpl @Inject constructor(
    private val serviceApi: ApiHelper
) : YGODeckBuilderRepository {


    override fun getAllCards() : Flow <UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = serviceApi.getCards()
            response.body()?.let { it ->
                emit((UIState.SUCCESS(it.cards.mapToDomainCards())))
            }
        }
        catch (e:Exception){
            emit(UIState.ERROR(e))
        }
    }

    override fun getCardsByType(cardType: CardType): Flow<UIState> = flow {
        emit(UIState.LOADING)

        try{
            val response = serviceApi.getCards()
            if(response.isSuccessful){
                response.body()?.let { it ->
                    emit(UIState.SUCCESS(it.cards.mapToDomainCards()))
                } //could add throw here
            }
            //could add if here for throw
        }catch (e:Exception){
            emit(UIState.ERROR(e))
        }

    }

    override fun getCardsByName(cardName: String): Flow<UIState> = flow {
        emit(UIState.LOADING)
        try {
            val response = serviceApi.getCards()
            if(response.isSuccessful){
                response.body()?.let { it ->
                    emit(UIState.SUCCESS(it.cards.mapToDomainCards()))
                }
            }
        }
        catch (e:Exception){
            emit(UIState.ERROR(e))
        }
    }
}