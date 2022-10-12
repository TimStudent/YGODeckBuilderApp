package com.example.ygodeckbuilder.adapter

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import com.example.ygodeckbuilder.data.domain.CardDomain
import com.example.ygodeckbuilder.ui.theme.YGODeckBuilderTheme

class CardAdapter(
    private var cardList: MutableList<CardDomain> = mutableListOf(),
    private val onCardClickListener: (CardDomain) -> Unit
) : RecyclerView.Adapter <CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cardList[position], onCardClickListener)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun update(newData: List<CardDomain>){
        cardList.clear()
        cardList.addAll(newData)
        notifyDataSetChanged()
    }
}

class CardViewHolder(
    val composeView: ComposeView
) : RecyclerView.ViewHolder(composeView) {
    fun bind(card: CardDomain, onCardClickListener: (CardDomain) -> Unit) {
        composeView.setContent {
            YGODeckBuilderTheme {

            }
        }
    }
}