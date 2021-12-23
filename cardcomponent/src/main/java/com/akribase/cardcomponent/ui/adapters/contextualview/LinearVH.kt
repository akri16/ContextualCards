package com.akribase.cardcomponent.ui.adapters.contextualview

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.akribase.cardcomponent.BR
import com.akribase.cardcomponent.R
import com.akribase.cardcomponent.models.renderable.RenderableCardGroup
import com.akribase.cardcomponent.utils.inflate
import com.akribase.cardcomponent.utils.inflater

class LinearVH(private val ll: LinearLayout): VH(ll) {
    private val layoutParams = LinearLayout.LayoutParams(
        0,
        ViewGroup.LayoutParams.MATCH_PARENT,
        1.0f
    )

    override fun bind(cardGroup: RenderableCardGroup) {
        super.bind(cardGroup)
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