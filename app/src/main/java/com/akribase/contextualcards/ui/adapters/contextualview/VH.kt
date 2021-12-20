package com.akribase.contextualcards.ui.adapters.contextualview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.utils.dpToPx

abstract class VH(view: ViewGroup, designType: DesignType) : RecyclerView.ViewHolder(view) {
    init {
        val height = view.context.dpToPx(designType.height).toInt()
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    }

    abstract fun bind(cardGroup: RenderableCardGroup)
}