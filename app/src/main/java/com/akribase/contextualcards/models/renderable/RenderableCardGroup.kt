package com.akribase.contextualcards.models.renderable

import android.content.Context
import com.akribase.contextualcards.models.data.Card
import com.akribase.contextualcards.models.data.CardGroup
import com.akribase.contextualcards.models.data.DesignType

data class RenderableCardGroup (
    val id: Int,
    val name: String,
    val designType: DesignType,
    val cards: List<RenderableCard>,
    val isScrollable: Boolean
){
    companion object {
        fun createFromCardGroup(cardGroup: CardGroup): RenderableCardGroup {
            val cards = cardGroup.cards.map { RenderableCard.createFromCard(it) }
            return with(cardGroup) {
                RenderableCardGroup(id, name, designType, cards, isScrollable)
            }
        }
    }
}