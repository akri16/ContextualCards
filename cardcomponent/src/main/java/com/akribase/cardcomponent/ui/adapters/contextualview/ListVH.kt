package com.akribase.cardcomponent.ui.adapters.contextualview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akribase.cardcomponent.R
import com.akribase.cardcomponent.models.renderable.RenderableCardGroup
import com.akribase.cardcomponent.ui.adapters.H3Remove
import com.akribase.cardcomponent.ui.adapters.SpacingItemDecoration
import com.akribase.cardcomponent.ui.adapters.cardgroup.CardGroupAdapter

internal class ListVH(
    rv: RecyclerView,
    onH3Remove: (H3Remove) -> Unit
): VH(rv) {
    private val adapter = CardGroupAdapter(
        {cardPos -> onH3Remove(H3Remove(adapterPosition, cardPos)) },
        {cardPos -> onH3Remove(H3Remove(adapterPosition, cardPos, true)) }
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