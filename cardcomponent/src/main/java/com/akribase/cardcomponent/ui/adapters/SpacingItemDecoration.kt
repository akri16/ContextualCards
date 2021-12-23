package com.akribase.cardcomponent.ui.adapters

import android.graphics.Rect
import android.view.View
import kotlin.jvm.JvmOverloads
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import java.lang.IllegalStateException

class SpacingItemDecoration @JvmOverloads constructor(
    private val spacingPx: Int,
    private val addStartSpacing: Boolean = false,
    private val addEndSpacing: Boolean = false
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (spacingPx <= 0) {
            return
        }
        if (addStartSpacing && parent.getChildLayoutPosition(view) < 1 || parent.getChildLayoutPosition(
                view
            ) >= 1
        ) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect.top = spacingPx
            } else {
                outRect.left = spacingPx
            }
        }
        if (addEndSpacing && parent.getChildAdapterPosition(view) == getTotalItemCount(parent) - 1) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect.bottom = spacingPx
            } else {
                outRect.right = spacingPx
            }
        }
    }

    private fun getTotalItemCount(parent: RecyclerView): Int {
        return parent.adapter?.itemCount ?: 0
    }

    private fun getOrientation(parent: RecyclerView): Int {
        return if (parent.layoutManager is LinearLayoutManager) {
            (parent.layoutManager as LinearLayoutManager).orientation
        } else {
            throw IllegalStateException("SpacingItemDecoration can only be used with a LinearLayoutManager.")
        }
    }
}