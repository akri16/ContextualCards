package com.akribase.cardcomponent.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.akribase.cardcomponent.R
import com.akribase.cardcomponent.models.data.CardGroup
import com.akribase.cardcomponent.models.renderable.RenderableCardGroup
import com.akribase.cardcomponent.ui.adapters.H3Remove
import com.akribase.cardcomponent.ui.adapters.contextualview.ContextualViewAdapter
import com.akribase.cardcomponent.utils.inflater

class CardComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs, R.style.BaseTheme) {

    var onHC3Remove: (H3Remove) -> Unit = {}
    var onFetch: () -> Unit = {}
    var isLoading
    get() = swr.isRefreshing
    set(value) { swr.isRefreshing = value}

    private val adapter = ContextualViewAdapter{ onHC3Remove(it) }
    private val recyclerView: RecyclerView
    private val swr: SwipeRefreshLayout

    init {
        swr = inflater.inflate(R.layout.card_component_view, this, false) as SwipeRefreshLayout
        recyclerView = swr.findViewById(R.id.rv)
        addView(swr)
        recyclerView.adapter = adapter

        swr.setOnRefreshListener { onFetch() }
    }

    fun render(cardGroups: List<CardGroup>) {
        val list = cardGroups.map { RenderableCardGroup.createFromCardGroup(it) }
        adapter.submitList(list)
    }

}