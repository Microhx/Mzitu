package com.xing.mzitu.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.xing.mzitu.R
import com.xing.mzitu.base.BaseFragment
import com.xing.mzitu.view.OnIndicatorDataListener
import com.xing.mzitu.view.TextViewNavigatorAdapter
import kotlinx.android.synthetic.main.fragment_home_layout.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/8
 *
 * version :
 *
 * desc : 主页
 */
abstract class AbstractTabFragment : BaseFragment(), OnIndicatorDataListener, ViewPager.OnPageChangeListener {

    override fun getResLayoutId(): Int = R.layout.fragment_home_layout

    override fun onLazyLoadData() {
        initIndicator()
        initViewPager()
    }

    private fun initIndicator() {
        val commonNavigator = CommonNavigator(context)
        commonNavigator.isAdjustMode = setNavigatorAdjustMode()
        commonNavigator.isIndicatorOnTop = true

        val indicator = TextViewNavigatorAdapter(this)
        commonNavigator.adapter = indicator
        id_tab_layout.navigator = commonNavigator
    }

    open fun setNavigatorAdjustMode(): Boolean = true

    private fun initViewPager() {
        id_home_view_pager.apply {
            adapter = InnerHomePagerAdapter(childFragmentManager)
            addOnPageChangeListener(this@AbstractTabFragment)
            offscreenPageLimit = getItemSize() - 1
        }
    }

    override fun getTitle(index: Int): CharSequence = getItemTitle(index)

    override fun onIndexClicked(index: Int) {
        id_home_view_pager.currentItem = index % getItemSize()
    }

    override fun getCount(): Int = getItemSize()

    override fun onPageScrollStateChanged(state: Int) {
        id_tab_layout.onPageScrollStateChanged(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        id_tab_layout.onPageScrolled(position,positionOffset,positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        id_tab_layout.onPageSelected(position)
    }

    inner class InnerHomePagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm){

        override fun getItem(position: Int): Fragment = getItemFragment(position)

        override fun getCount(): Int = getItemSize()
    }

    abstract fun getItemSize() : Int

    abstract fun getItemTitle(position:Int) : CharSequence

    abstract fun getItemFragment(position:Int) : Fragment


}