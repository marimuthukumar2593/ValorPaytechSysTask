package com.example.valorpaytechsystask.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecoration: RecyclerView.ItemDecoration() {

    private val verticalOrientation = true
    private val space = 10
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != 0) {
            if (verticalOrientation) {
                outRect.set(space, 0, 0, 0);
            } else if (!verticalOrientation) {
                outRect.set(0, space, 0, 0);
            }
        }
    }


//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
//    RecyclerView.State state) {
//        // Skip first item in the list
//        if (parent.getChildAdapterPosition(view) != 0) {
//            if (verticalOrientation) {
//                outRect.set(space, 0, 0, 0);
//            } else if (!verticalOrientation) {
//                outRect.set(0, space, 0, 0);
//            }
//        }
//    }
}
