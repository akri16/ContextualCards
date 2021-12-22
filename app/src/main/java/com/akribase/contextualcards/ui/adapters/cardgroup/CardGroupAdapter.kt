package com.akribase.contextualcards.ui.adapters.cardgroup

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akribase.contextualcards.BR
import com.akribase.contextualcards.R
import com.akribase.contextualcards.databinding.CardHc3Binding
import com.akribase.contextualcards.models.data.DesignType
import com.akribase.contextualcards.models.renderable.RenderableCard
import com.akribase.contextualcards.models.renderable.RenderableCardGroup
import com.akribase.contextualcards.utils.dpToPx
import com.akribase.contextualcards.utils.getScreenWidth
import com.akribase.contextualcards.utils.inflater

class CardGroupAdapter(
    val onHC3Dismiss: (Int) -> Unit = {},
    val onHC3Remind: (Int) -> Unit = {},
) : ListAdapter<RenderableCard, CardGroupAdapter.VH>(RenderableCard.DIFFCALLBACK) {
    var cardGroup: RenderableCardGroup? = null
        set(value) {
            super.submitList(value?.cards)
            field = value
        }

    override fun submitList(list: MutableList<RenderableCard>?) {
        throw UnsupportedOperationException("Set cardGroup using its setter. This method should not be called")
    }

    inner class VH(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: RenderableCard) {
            val designType = cardGroup?.designType

            // Full-Screen width for all but HC9 and dynamic width for HC9 cards
            if (designType != DesignType.HC9) {
                binding.root.layoutParams = ViewGroup.LayoutParams(
                    (getScreenWidth() - binding.root.context.resources.getDimension(R.dimen.layout_padding) * 2).toInt(),
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            } else {
                val width = card.bg.aspectRatio?.let { cardGroup?.height?.times(it) }
                width?.let {
                    binding.root.layoutParams = ViewGroup.LayoutParams(
                        binding.root.context.dpToPx(it.toInt()).toInt(),
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }

            (binding as? CardHc3Binding)?.apply {
                binding.ml.progress = 0f
                dismissBtn.setOnClickListener { onHC3Dismiss(adapterPosition) }
                remindBtn.setOnClickListener { onHC3Remind(adapterPosition) }
            }


            binding.setVariable(BR.card, card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        DataBindingUtil.inflate(
            parent.inflater, cardGroup?.designType?.layout ?: 0, null, false
        )
    )

    override fun getItemViewType(position: Int): Int {
        return cardGroup?.designType?.layout ?: 0
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))
}