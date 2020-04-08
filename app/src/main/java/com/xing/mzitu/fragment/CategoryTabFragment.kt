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
class CategoryTabFragment : AbstractTabFragment() {

    override fun getItemSize(): Int = CATEGORY_TITLE_LIST.size

    override fun getItemTitle(position: Int): CharSequence = CATEGORY_TITLE_LIST[position % CATEGORY_TITLE_LIST.size]

    override fun getItemFragment(position: Int): Fragment = MainPageFragment.getInstance(
                                        CATEGORY_URL_LIST[position % CATEGORY_URL_LIST.size])

    companion object {
        val CATEGORY_TITLE_LIST = arrayListOf("性感妹子","日本妹子","台湾妹子","清纯妹子")
        val CATEGORY_URL_LIST   = arrayListOf("xinggan","japan","taiwan","mm")
    }

}