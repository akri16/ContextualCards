package com.akribase.contextualcards.ui.adapters.contextualview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.ui.adapters.SpacingItemDecoration
import com.akribase.contextualcards.ui.adapters.cardgroup.CardGroupAdapter
import com.akribase.contextualcards.utils.dpToPx

class ListVH(private val rv: RecyclerView, designType: DesignType): VH(rv, designType) {
    init {
        rv.layoutManager = LinearLayoutManager(
            rv.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rv.addItemDecoration(SpacingItemDecoration(rv.context.dpToPx(15).toInt()))
    }

    override fun bind(cardGroup: RenderableCardGroup) {
        rv.adapter = CardGroupAdapter(cardGroup.cards, cardGroup.designType)
    }
}