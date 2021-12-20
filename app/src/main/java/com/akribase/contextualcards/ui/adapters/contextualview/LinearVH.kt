package com.akribase.contextualcards.ui.adapters.contextualview

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.akribase.contextualcards.BR
import com.akribase.contextualcards.R
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.utils.inflate
import com.akribase.contextualcards.utils.inflater

class LinearVH(private val ll: LinearLayout, designType: DesignType) : VH(ll, designType) {
    private val layoutParams = LinearLayout.LayoutParams(
        0,
        ViewGroup.LayoutParams.MATCH_PARENT,
        1.0f
    )

    override fun bind(cardGroup: RenderableCardGroup) {
        ll.removeAllViews()
        val layoutId = cardGroup.designType.layout
        cardGroup.cards.forEach {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                ll.inflater, layoutId, null, false
            )
            binding.setVariable(BR.card, it)
            binding.executePendingBindings()
            ll.addView(binding.root, layoutParams)
            ll.addView(ll.inflate(R.layout.space_view))
        }
        ll.removeViewAt(ll.size - 1)
    }
}