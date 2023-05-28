package com.example.valorpaytechsystask.ui.postdetail


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valorpaytechsystask.R
import com.example.valorpaytechsystask.adapter.CommentsAdapter
import com.example.valorpaytechsystask.adapter.PostViewAdapter
import com.example.valorpaytechsystask.model.comments.Comments
import com.example.valorpaytechsystask.model.posts.PostDetails
import com.example.valorpaytechsystask.ui.home.HomeViewModel
import com.example.valorpaytechsystask.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class PostViewFragment : Fragment(), PostViewAdapter.OnPostItemClicked {
    var postRecyclerView: RecyclerView?=null
    var postdetailDapter: PostViewAdapter?=null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    var postdetails:ArrayList<PostDetails>?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_post, container, false)
        postRecyclerView=root.findViewById(R.id.post_recyclerview)


      /*  var postdetails = */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                postdetails =  arguments?.getParcelableArrayList(Constants.BUNDLE_POST, PostDetails::class.java)
        } else {
                postdetails = arguments?.getParcelableArrayList(Constants.BUNDLE_POST)
        }
        if(postdetails!=null){
            Toast.makeText(requireContext(),"User name == ${postdetails?.size}", Toast.LENGTH_SHORT).show()
            ViewCompat.setNestedScrollingEnabled(postRecyclerView!!, false);
            postRecyclerView?.isNestedScrollingEnabled=true
            postRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
            initAdapter(postdetails!!)
        }


        return root
    }



    fun initAdapter(mList: List<PostDetails>){
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        postdetailDapter = PostViewAdapter(mList as ArrayList<PostDetails>, homeViewModel,this)

        // Setting the Adapter with the recyclerview
        postRecyclerView?.adapter = postdetailDapter
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
               R.drawable.divider
            )!!
        )
        postRecyclerView?.addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))

//        postRecyclerView?.addItemDecoration(dividerItemDecoration)
    }

    override fun onPostClicked(pos: Int, user: PostDetails,list: ArrayList<Comments>) {

        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.bottom_sheet_comments, null)
        var close = view.findViewById<ImageButton>(R.id.img_close)
        var comments = view.findViewById<TextView>(R.id.tv_post_comments)
        var commentsreccylerview = view.findViewById<RecyclerView>(R.id.comments_recyclerview)
        var commentsdetailDapter =CommentsAdapter(list)
                    commentsreccylerview?.layoutManager = LinearLayoutManager(commentsreccylerview.context)
                    commentsreccylerview.adapter =commentsdetailDapter

        comments.text = "Comments : ${list.size}"

        close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)

        dialog.setContentView(view)
        dialog.show()

    }


}