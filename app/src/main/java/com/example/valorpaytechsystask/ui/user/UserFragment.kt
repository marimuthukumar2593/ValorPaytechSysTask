package com.example.valorpaytechsystask.ui.user

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.valorpaytechsystask.R
import com.example.valorpaytechsystask.model.alluser.UserDetails
import com.example.valorpaytechsystask.model.posts.PostDetails
import com.example.valorpaytechsystask.ui.home.HomeViewModel
import com.example.valorpaytechsystask.utils.Constants
import com.example.valorpaytechsystask.utils.Constants.BUNDLE_POST
import com.example.valorpaytechsystask.utils.Constants.BUNDLE_USER
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class UserFragment: Fragment()  {
    private lateinit var homeViewModel: HomeViewModel

    var btbViewPost:Button? =null
    var userid =""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_user, container, false)
        btbViewPost = root.findViewById(R.id.bnt_viewpost)
        val userdetails = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(BUNDLE_USER,UserDetails::class.java)
        } else {
            arguments?.getParcelable(BUNDLE_USER)
        }

        if(userdetails!=null){
            userid=userdetails.id!!
        }

        btbViewPost?.setOnClickListener {
            if(Constants.checkForInternet(requireContext())) {
                fetchPost(userid)
            }
                else{
                showNoInternetAlert("Please check your internet connection")
            }

        }


        return root
    }

    fun showNoInternetAlert(msg: String) {
        val dialogBuilder = AlertDialog.Builder(requireContext())


        dialogBuilder.setPositiveButton("Retry"){dialogInterface, which ->
            fetchPost(userid)
            dialogInterface.dismiss()
        }

        dialogBuilder.setNegativeButton("Ok"){dialogInterface, which ->
            dialogInterface.dismiss()
        }

        // create dialog box
        // set title for alert dialog box
        val alert = dialogBuilder.create()
        alert.setTitle("No Internet Connection")
        alert.setMessage(msg)
        alert.show()
    }

    fun fetchPost(userid:String) {
        homeViewModel.fetchAllPosts(userid)
            .observeOn(AndroidSchedulers.mainThread()) // list of challenges which contain challenge ID
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    loadPostviewFragment(it)
                }
            )
    }

    fun loadPostviewFragment(postDetails: List<PostDetails>) {
        val bundle = Bundle()
        bundle.putParcelableArrayList(BUNDLE_POST, postDetails as ArrayList<out Parcelable>)
        val nav= requireActivity().findNavController(R.id.nav_host_fragment)
        nav.navigate(R.id.navigation_post,bundle)
    }
}