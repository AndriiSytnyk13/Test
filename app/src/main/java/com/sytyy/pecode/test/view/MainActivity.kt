package com.sytyy.pecode.test.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sytyy.pecode.test.databinding.ActivityMainBinding
import com.sytyy.pecode.test.view.MainFragment.Companion.mainAdapter
import com.sytyy.pecode.test.view.MainFragment.Companion.newInstance
import com.sytyy.pecode.test.view.MainFragment.Companion.pageId

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        mainAdapter = MainViewPagerAdapter().apply {
            addFragment()
        }
        mainActivityBinding.viewPager.adapter = mainAdapter
    }

    override fun onBackPressed() {
        mainActivityBinding.viewPager.apply {
            if (currentItem == 0) {
                super.onBackPressed()
            } else
                currentItem -= 1
        }
    }


    inner class MainViewPagerAdapter(
        fragmentManager: FragmentManager = supportFragmentManager
    ) :
        FragmentPagerAdapter(fragmentManager) {

        private val fragments = mutableListOf<MainFragment>()

        @RequiresApi(Build.VERSION_CODES.O)
        fun addFragment() {
            fragments.add(newInstance(pageId))
            notifyDataSetChanged()
            mainActivityBinding.viewPager.currentItem = pageId++
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun removeFragment() {
            fragments.removeAt(fragments.size - 1)
            notifyDataSetChanged()
            mainActivityBinding.viewPager.currentItem = pageId--
        }

        override fun getCount(): Int = fragments.size

        override fun getItem(position: Int) = fragments[position]
    }
}