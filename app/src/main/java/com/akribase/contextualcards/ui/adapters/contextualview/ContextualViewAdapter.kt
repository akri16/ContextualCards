package com.akribase.contextualcards.ui.adapters.contextualview

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.R
import com.akribase.contextualcards.models.data.CardGroup
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.ui.adapters.H3Remove
import com.akribase.contextualcards.utils.inflate
import kotlin.math.abs

class ContextualViewAdapter(val onH3Remove: (H3Remove) -> Unit): ListAdapter<RenderableCardGroup, VH>(RenderableCardGroup.DIFFCALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return if(viewType < 0) {
            LinearVH(parent.inflate(R.layout.card_group_linear) as LinearLayout)
        } else {
            ListVH(parent.inflate(R.layout.card_group_rv) as RecyclerView, onH3Remove)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isScrollable) 1 else -1
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))
}