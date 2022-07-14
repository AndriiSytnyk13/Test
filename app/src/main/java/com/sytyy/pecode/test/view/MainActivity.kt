package com.sytyy.pecode.test.view

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sytyy.pecode.test.R
import com.sytyy.pecode.test.databinding.ActivityMainBinding
import com.sytyy.pecode.test.view.MainFragment.Companion.mainAdapter
import com.sytyy.pecode.test.view.MainFragment.Companion.pageId

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding

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
                pageId = 1
                super.onBackPressed()
            } else
                currentItem -= 1
        }
    }


    inner class MainViewPagerAdapter(
        fragmentManager: FragmentManager = supportFragmentManager,
        private val fragments:MutableList<MainFragment> = mutableListOf()
    ) :
        FragmentStatePagerAdapter(fragmentManager) {


        fun addFragment() {
            fragments.add(MainFragment())
            notifyDataSetChanged()
            mainActivityBinding.viewPager.currentItem = pageId++
        }

        fun removeFragment() {
            fragments.removeAt(fragments.size - 1)
            notifyDataSetChanged()
            mainActivityBinding.viewPager.currentItem = pageId--
        }

        override fun getCount() = fragments.size


        override fun getItem(position: Int): MainFragment {
            val fragment = MainFragment().apply {
                arguments = Bundle().apply {
                    putInt(R.string.fragmentsPackage.toString(), position + 1)
                }
            }
            return fragment
        }
    }
}