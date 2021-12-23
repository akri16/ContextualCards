package com.akribase.cardcomponent.models.renderable

import androidx.recyclerview.widget.DiffUtil
import com.akribase.cardcomponent.models.data.CardGroup
import com.akribase.cardcomponent.models.data.DesignType

data class RenderableCardGroup (
    val id: Int,
    val name: String,
    val designType: DesignType,
    val cards: List<RenderableCard>,
    val isScrollable: Boolean,
    val height: Int?
){
    companion object {

        val DIFFCALLBACK = object: DiffUtil.ItemCallback<RenderableCardGroup>() {
            override fun areItemsTheSame(
                oldItem: RenderableCardGroup,
                newItem: RenderableCardGroup
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RenderableCardGroup,
                newItem: RenderableCardGroup
            ) = oldItem.designType == newItem.designType && oldItem.isScrollable == newItem.isScrollable
        }

        fun createFromCardGroup(cardGroup: CardGroup): RenderableCardGroup {
            val cards = cardGroup.cards.map { RenderableCard.createFromCard(it) }
            return with(cardGroup) {
                val isScrollable = if (designType in arrayOf(DesignType.HC9, DesignType.HC3)) true else isScrollable
                RenderableCardGroup(id, name, designType, cards, isScrollable, height)
            }
        }
    }
}