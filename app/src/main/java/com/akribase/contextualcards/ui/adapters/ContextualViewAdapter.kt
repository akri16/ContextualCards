package com.akribase.contextualcards.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.BR
import com.akribase.contextualcards.R
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.utils.inflate
import com.akribase.contextualcards.utils.inflater
import kotlin.math.abs

class ContextualViewAdapter(
    var cardGroups: List<RenderableCardGroup>
): RecyclerView.Adapter<ContextualViewAdapter.VH>() {

    abstract class VH(view: View, layoutId: Int) : RecyclerView.ViewHolder(view) {
        abstract fun bind(cardGroup: RenderableCardGroup)
    }

    class LinearVH(private val ll: LinearLayout, layoutId: Int) : VH(ll, layoutId) {
        private val layoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT,
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
            }
        }
    }

    class ListVH(private val rv: RecyclerView, layoutId: Int) : VH(rv, layoutId) {
        init {
            rv.layoutManager = LinearLayoutManager(
                rv.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rv.addItemDecoration(SpacingItemDecoration(40))
        }

        override fun bind(cardGroup: RenderableCardGroup) {
            rv.adapter = CardGroupAdapter(cardGroup.cards, cardGroup.designType.layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val isScrollable = viewType > 0
        val layoutId = abs(viewType)

        return if(!isScrollable) {
            LinearVH(parent.inflate(R.layout.card_group_linear) as LinearLayout, layoutId)
        } else {
            ListVH(parent.inflate(R.layout.card_group_rv) as RecyclerView, layoutId)
        }
    }

    override fun getItemCount() = cardGroups.size

    override fun getItemViewType(position: Int): Int {
        val isScrollable = if (cardGroups[position].isScrollable) 1 else -1
        return cardGroups[position].designType.layout * isScrollable
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(cardGroups[position])
}