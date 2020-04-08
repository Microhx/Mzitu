package com.xing.mzitu.view;

/**
 * created by xinghe
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 2020-02-09
 * <p>
 * version :
 * <p>
 * desc :
 */
public interface OnIndicatorDataListener {

    int getCount();

    CharSequence getTitle(int index);

    void onIndexClicked(int index);
}
