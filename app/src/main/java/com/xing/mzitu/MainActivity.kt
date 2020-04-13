package com.xing.mzitu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.xing.mzitu.fragment.AbstractTabFragment
import com.xing.mzitu.fragment.CategoryTabFragment
import com.xing.mzitu.fragment.HomeTabFragment
import com.xing.mzitu.fragment.MainPageFragment
import com.xing.mzitu.view.tab.RadioLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RadioLayout.onRadioButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()
    }

    private fun initViewPager() {
        id_view_pager.apply {
            adapter = MainPageFragmentAdapter(supportFragmentManager)
            offscreenPageLimit = FRAGMENT_SIZE - 1
        }

        id_radio_layout.setRadioButtonClickListener(this)
    }

    inner class MainPageFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> HomeTabFragment()
                1 -> CategoryTabFragment()
                else -> Fragment()
            }
        }

        override fun getCount(): Int = FRAGMENT_SIZE
    }

    override fun onRadioButtonClick(index: Int) {
        id_view_pager.setCurrentItem(index % FRAGMENT_SIZE, false)
    }

    companion object {
        const val FRAGMENT_SIZE = 4
    }

}

