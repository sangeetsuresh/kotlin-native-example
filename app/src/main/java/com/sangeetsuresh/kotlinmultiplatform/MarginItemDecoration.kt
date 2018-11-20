package com.sangeetsuresh.kotlinmultiplatform

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            top = spaceHeight
            left = spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}