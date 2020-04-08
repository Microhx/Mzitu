package com.xing.mzitu.fragment

import androidx.fragment.app.Fragment

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/8
 *
 * version :
 *
 * desc :
 */
class HomeTabFragment : AbstractTabFragment() {

    override fun getItemSize(): Int = HOME_TITLE_LIST.size

    override fun getItemTitle(position: Int): CharSequence = HOME_TITLE_LIST[position % HOME_TITLE_LIST.size]

    override fun getItemFragment(position: Int): Fragment = MainPageFragment.getInstance(HOME_URL_LIST[position % HOME_URL_LIST.size])

    companion object {
        val HOME_TITLE_LIST = arrayListOf("最新","最热","推荐")
        val HOME_URL_LIST   = arrayListOf("","hot","best")
    }
}