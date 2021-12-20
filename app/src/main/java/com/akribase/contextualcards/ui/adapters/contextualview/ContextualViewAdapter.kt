package com.akribase.contextualcards.ui.adapters.contextualview

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.R
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.utils.inflate
import kotlin.math.abs

class ContextualViewAdapter(
    var cardGroups: List<RenderableCardGroup>
): RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val isScrollable = viewType > 0
        val layoutId = abs(viewType)

        return if(!isScrollable) {
            LinearVH(parent.inflate(R.layout.card_group_linear) as LinearLayout,
                DesignType.getTypeFromLayout(layoutId))
        } else {
            ListVH(parent.inflate(R.layout.card_group_rv) as RecyclerView,
                DesignType.getTypeFromLayout(layoutId))
        }
    }

    override fun getItemCount() = cardGroups.size

    override fun getItemViewType(position: Int): Int {
        val isScrollable = if (cardGroups[position].isScrollable) 1 else -1
        return cardGroups[position].designType.layout * isScrollable
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(cardGroups[position])
}