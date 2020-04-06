package com.xing.mzitu.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/21
 *
 * version : 1.0.1
 *
 * desc :
 *
 *
 */
abstract class BaseCommonAdapter<T>(list: MutableList<T>, layoutId:Int) : BaseQuickAdapter<T, BaseViewHolder>(layoutId,list) {
    constructor(layoutId:Int) : this(ArrayList<T>(), layoutId)

}
