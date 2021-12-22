package com.akribase.contextualcards.ui.adapters.contextualview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.utils.dpToPx

open class VH(val view: ViewGroup) : RecyclerView.ViewHolder(view) {
    open fun bind(cardGroup: RenderableCardGroup) {
        // Use height attribute if available (HC9) else use the fixed ones
        val height = view.context.dpToPx(cardGroup.height ?: cardGroup.designType.height).toInt()
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    }
}