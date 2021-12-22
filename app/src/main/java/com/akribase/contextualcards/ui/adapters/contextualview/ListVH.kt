package com.akribase.contextualcards.ui.adapters.contextualview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.R
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.ui.adapters.H3Remove
import com.akribase.contextualcards.ui.adapters.SpacingItemDecoration
import com.akribase.contextualcards.ui.adapters.cardgroup.CardGroupAdapter

class ListVH(
    rv: RecyclerView,
    onH3Remove: (H3Remove) -> Unit
): VH(rv) {
    private val adapter = CardGroupAdapter(
        {cardPos -> onH3Remove(H3Remove(adapterPosition, cardPos, true)) },
        {cardPos -> onH3Remove(H3Remove(adapterPosition, cardPos)) }
    )

    init {
        rv.layoutManager = LinearLayoutManager(
            rv.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rv.addItemDecoration(SpacingItemDecoration(rv.context.resources.getDimension(R.dimen.card_space).toInt()))
        rv.adapter = adapter
    }

    override fun bind(cardGroup: RenderableCardGroup) {
        super.bind(cardGroup)
        adapter.cardGroup = cardGroup
    }
}