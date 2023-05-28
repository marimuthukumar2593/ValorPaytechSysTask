package com.example.valorpaytechsystask.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valorpaytechsystask.R
import com.example.valorpaytechsystask.R.*
import com.example.valorpaytechsystask.adapter.UserAdapter
import com.example.valorpaytechsystask.decoration.VerticalItemDecoration
import com.example.valorpaytechsystask.model.alluser.UserDetails
import com.example.valorpaytechsystask.model.posts.PostDetails
import com.example.valorpaytechsystask.utils.Constants
import com.example.valorpaytechsystask.utils.Constants.BUNDLE_USER
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class HomeFragment : Fragment(),UserAdapter.OnItemClicked {

    private lateinit var homeViewModel: HomeViewModel
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()


    var mRootView:LinearLayout?=null

    var userRecyclerView:RecyclerView?=null
    var userdetailDapter:UserAdapter?=null
    var progressBar:AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        val root = inflater.inflate(layout.fragment_home, container, false)

        mRootView=root.findViewById<LinearLayout>(R.id.layout)
         userRecyclerView = root.findViewById<RecyclerView>(R.id.user_recyclerview)

        userRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        userRecyclerView?.addItemDecoration(VerticalItemDecoration())

        fetchAllUser()

        return root
    }

    fun fetchAllUser(){
        if(Constants.checkForInternet(requireContext())) {
            progressBar?.show()
            homeViewModel.fetchAllusers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { }
                .subscribe(

                    {

                        println("User details === ${it}")
                        if (!it.isNullOrEmpty()) {
                            initAdapter(it)
                        }
                    },
                    {
                        progressBar?.dismiss()
                    },
                    {

                    },
                    {

                    }
                )
        }
        else{
            showNoInternetAlert("Please check your internet connection")
        }
    }

    fun initAdapter(mList: List<UserDetails>){
        progressBar?.dismiss()
        userdetailDapter = UserAdapter(mList,this)

        // Setting the Adapter with the recyclerview
        userRecyclerView?.adapter = userdetailDapter
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClicked(pos: Int, user: UserDetails) {
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_USER, user)
        val nav= requireActivity().findNavController(R.id.nav_host_fragment)
        nav.navigate(R.id.navigation_user,bundle)
    }


    fun showLoadingDialog(){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val  customLayout:View = layoutInflater.inflate(R.layout.porgress_bar, null);
        dialogBuilder.setView(customLayout);
        progressBar = dialogBuilder.create()

    }


    fun showNoInternetAlert(msg: String) {
        val dialogBuilder = AlertDialog.Builder(requireContext())


        dialogBuilder.setPositiveButton("Retry"){dialogInterface, which ->
            fetchAllUser()
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
}