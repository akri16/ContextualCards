package com.akribase.contextualcards.models.renderable

import android.content.Context
import com.akribase.contextualcards.models.data.Card
import com.akribase.contextualcards.models.data.CardGroup
import com.akribase.contextualcards.models.data.DesignType
import timber.log.Timber

data class RenderableCardGroup (
    val id: Int,
    val name: String,
    val designType: DesignType,
    val cards: List<RenderableCard>,
    val isScrollable: Boolean,
    val height: Int
){
    companion object {
        fun createFromCardGroup(cardGroup: CardGroup): RenderableCardGroup {
            Timber.d(cardGroup.toString())
            val cards = cardGroup.cards.map { RenderableCard.createFromCard(it, cardGroup.height) }
            return with(cardGroup) {
                val isScrollable = if (designType == DesignType.HC9) true else isScrollable
                RenderableCardGroup(id, name, designType, cards, isScrollable, height)
            }
        }
    }
}