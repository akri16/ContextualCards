package com.akribase.contextualcards.ui.adapters

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.BR
import com.akribase.contextualcards.R
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.utils.dpToPx
import com.akribase.contextualcards.utils.inflate
import com.akribase.contextualcards.utils.inflater
import kotlin.math.abs

class ContextualViewAdapter(
    var cardGroups: List<RenderableCardGroup>
): RecyclerView.Adapter<ContextualViewAdapter.VH>() {

    abstract class VH(view: ViewGroup, designType: DesignType) : RecyclerView.ViewHolder(view) {
        init {
            val height = view.context.dpToPx(designType.height).toInt()
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
        }

        abstract fun bind(cardGroup: RenderableCardGroup)
    }

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

    class ListVH(private val rv: RecyclerView, designType: DesignType): VH(rv, designType) {
        init {
            rv.layoutManager = LinearLayoutManager(
                rv.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rv.addItemDecoration(SpacingItemDecoration(rv.context.dpToPx(12).toInt()))
        }

        override fun bind(cardGroup: RenderableCardGroup) {
            rv.adapter = CardGroupAdapter(cardGroup.cards, cardGroup.designType.layout)
        }
    }

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