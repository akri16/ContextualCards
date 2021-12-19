package com.akribase.contextualcards.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.BR
import com.akribase.contextualcards.R
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import inflate
import inflater

class ContextualViewAdapter(
    var cardGroups: List<RenderableCardGroup>
): RecyclerView.Adapter<ContextualViewAdapter.VH>() {

    abstract class VH(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(cardGroup: RenderableCardGroup)
    }

    class LinearVH(private val ll: LinearLayout) : VH(ll) {
        private val layoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.MATCH_PARENT,
            1.0f
        )

        override fun bind(cardGroup: RenderableCardGroup) {
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

    class ListVH(private val rv: RecyclerView) : VH(rv) {
        override fun bind(cardGroup: RenderableCardGroup) {
            rv.adapter = CardGroupAdapter(cardGroup.cards, cardGroup.designType.layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return if(viewType == 0) {
            LinearVH(parent.inflate(R.layout.card_group_linear) as LinearLayout)
        } else {
            ListVH(parent.inflate(R.layout.card_group_rv) as RecyclerView)
        }
    }

    override fun getItemCount() = cardGroups.size

    override fun getItemViewType(position: Int): Int {
        return if (cardGroups[position].isScrollable) 1 else 0
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(cardGroups[position])
}