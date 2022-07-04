package com.sytyy.pecode.test.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.sytyy.pecode.test.R
import com.sytyy.pecode.test.databinding.FragmentMainBinding
import com.sytyy.pecode.test.notification.MainNotification


@RequiresApi(Build.VERSION_CODES.O)
class MainFragment : Fragment(R.layout.fragment_main) {

    private var mainFragmentBinding: FragmentMainBinding? = null
    private val notification: MainNotification by lazy {
        MainNotification(requireContext())
    }


    companion object {
        var mainAdapter: MainActivity.MainViewPagerAdapter? = null

        var pageId = 1

        @JvmStatic
        fun newInstance(pageNumber: Int): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putInt(R.string.fragmentsPackage.toString(), pageNumber)
                }
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentBinding = FragmentMainBinding.bind(view)
        getFragmentArguments()
        addFragment()
        removeFragment()
        createNotification()

    }

    private fun createNotification() {
        mainFragmentBinding?.createNewNotificationButton?.setOnClickListener {
            val notificationManager = NotificationManagerCompat.from(requireContext())
            val pageNumber = arguments?.getInt(R.string.fragmentsPackage.toString(), pageId)
            notificationManager.notify(
                notification.notificationId,
                notification.createNotification(pageNumber!!)
            )
        }
    }

    private fun getFragmentArguments() {
        val pageNumber = arguments?.getInt(R.string.fragmentsPackage.toString(), pageId)
        mainFragmentBinding?.fragmentNumberTextView?.text = pageNumber.toString()
        if (pageNumber == 1) {
            mainFragmentBinding?.removeFragmentButton?.visibility = View.GONE
        } else {
            mainFragmentBinding?.removeFragmentButton?.visibility = View.VISIBLE
        }
    }

    private fun removeFragment() {
        mainFragmentBinding?.removeFragmentButton?.setOnClickListener {
            mainAdapter?.removeFragment()
        }
    }

    private fun addFragment() {
        mainFragmentBinding?.addButtonFragment?.setOnClickListener {
            mainAdapter?.addFragment()
        }
    }

}