package com.example.alexlindroos.readit.ui.shared

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.graphics.drawable.Drawable
import com.example.alexlindroos.readit.R


/**
 * Created by Alex Lindroos on 13/02/2018.
 */

class SimpleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val mDivider: Drawable = context.getDrawable(R.drawable.line_divider)

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
    }
}