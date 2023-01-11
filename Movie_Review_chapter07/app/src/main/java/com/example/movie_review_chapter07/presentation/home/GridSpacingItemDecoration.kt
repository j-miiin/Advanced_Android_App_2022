package com.example.movie_review_chapter07.presentation.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapterPosition = parent.getChildAdapterPosition(view)
        val gridLayoutManager = parent.layoutManager as GridLayoutManager
        val spanSize = gridLayoutManager.spanSizeLookup.getSpanSize(adapterPosition)

        if (spanSize == spanCount) {    // 통째로 쓰겠다
            outRect.left = spacing  // 여백
            outRect.right = spacing
            outRect.top = spacing
            outRect.bottom = spacing
            return
        }

        val column = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        /*** 인덱스가 원래는
              0
              1
          2   3   4
          5   6   7
          이런 식이었다면
              0
              0
          0   1   2
          0   1   2
        이렇게 만들어줌 -> 일정한 인덱스 사용 가능 ***/


        val itemHorizontalSpacing = ((spanCount + 1) * spacing) / spanCount.toFloat()
        when (column) {
            0 -> {  // 3개 중에서 왼쪽
                outRect.left = spacing
                outRect.right = (itemHorizontalSpacing - spanSize).toInt()
            }
            (spanCount - 1) -> {    // 중간
                outRect.left = (itemHorizontalSpacing - spanSize).toInt()
            }
            else -> {   // 오른쪽
                outRect.left = (itemHorizontalSpacing / 2).toInt()
                outRect.right = (itemHorizontalSpacing / 2).toInt()
            }
        }
        outRect.top = spacing
        outRect.bottom = spacing
    }
}