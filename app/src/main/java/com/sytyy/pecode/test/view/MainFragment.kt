package com.sytyy.pecode.test.view

import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.sytyy.pecode.test.R
import com.sytyy.pecode.test.databinding.FragmentMainBinding
import com.sytyy.pecode.test.notification.MainNotification


class MainFragment : Fragment(R.layout.fragment_main) {

    private var mainFragmentBinding: FragmentMainBinding? = null

    private val notificationManager by lazy {
        NotificationManagerCompat.from(requireContext())
    }

    private lateinit var notification: MainNotification

    companion object {
        var mainAdapter: MainActivity.MainViewPagerAdapter? = null
        var pageId = 1
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notification = MainNotification(pageId)
        mainFragmentBinding = FragmentMainBinding.bind(view)
        getFragmentArguments()
        addFragment()
        removeFragment()
        createNotification()

    }

    private fun createNotification() {
        mainFragmentBinding?.createNewNotificationButton?.setOnClickListener {
            notification.createNotificationChannel(requireContext())
            val pageNumber = arguments?.getInt(R.string.fragmentsPackage.toString(), pageId)
            notificationManager.notify(
                notification.notificationId,
                notification.createNotification(requireContext(), pageNumber!!)
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
            notificationManager.cancel(notification.notificationId)
        }
    }

    private fun addFragment() {
        mainFragmentBinding?.addButtonFragment?.setOnClickListener {
            mainAdapter?.addFragment()
        }
    }

}