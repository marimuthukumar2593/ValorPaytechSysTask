package com.example.valorpaytechsystask.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.valorpaytechsystask.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomBottomSheetDialogFragment: BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =inflater.inflate(R.layout.bottom_sheet_comments, container, false)

        val layout =root.findViewById<LinearLayout>(R.id.bottomSheet)
        val bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(layout)
        val metrics = resources.displayMetrics
        bottomSheetBehavior.peekHeight = metrics.heightPixels / 2
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        return root
    }

}