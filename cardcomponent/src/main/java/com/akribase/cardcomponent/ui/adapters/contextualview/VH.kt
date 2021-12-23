package com.akribase.cardcomponent.ui.adapters.contextualview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akribase.cardcomponent.models.renderable.RenderableCardGroup
import com.akribase.cardcomponent.utils.dpToPx

open class VH(val view: ViewGroup) : RecyclerView.ViewHolder(view) {
    open fun bind(cardGroup: RenderableCardGroup) {
        // Use height attribute if available (HC9) else use the fixed ones
        val height = view.context.dpToPx(cardGroup.height ?: cardGroup.designType.height).toInt()
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    }
}