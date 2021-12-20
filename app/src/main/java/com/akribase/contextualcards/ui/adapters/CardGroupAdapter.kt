package com.akribase.contextualcards.ui.adapters

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.BR
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCard
import com.akribase.contextualcards.utils.getScreenWidth
import com.akribase.contextualcards.utils.inflater

class CardGroupAdapter(
    private val cards: List<RenderableCard>,
    private val layoutId: Int
): RecyclerView.Adapter<CardGroupAdapter.VH>() {

    inner class VH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            if (DesignType.getTypeFromLayout(layoutId) == DesignType.HC5) {
                binding.root.layoutParams = ViewGroup.LayoutParams(
                    getScreenWidth() - 30,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }

        fun bind(card: RenderableCard) {
            binding.setVariable(BR.card, card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(DataBindingUtil.inflate(
        parent.inflater, layoutId, null, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(cards[position])

    override fun getItemCount() = cards.size
}