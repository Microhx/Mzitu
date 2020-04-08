package com.xing.mzitu.view;

import android.content.Context;
import android.graphics.Color;

import com.xing.mzitu.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

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
public class TextViewNavigatorAdapter extends CommonNavigatorAdapter {

    private static final int NORMAL_COLOR = Color.parseColor("#717171");
    private static final int SELECT_COLOR = Color.parseColor("#272727");

    private OnIndicatorDataListener mDataListener;

    public TextViewNavigatorAdapter(OnIndicatorDataListener mDataListener) {
        this.mDataListener = mDataListener;
    }

    @Override
    public int getCount() {
        return null == mDataListener ? 0 : mDataListener.getCount();
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setColors(context.getResources().getColor(R.color.colorPrimary));
        indicator.setXOffset(0);
        indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);

        return indicator;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        SimplePagerTitleView titleView = new SimplePagerTitleView(context);
        titleView.setNormalColor(NORMAL_COLOR);
        titleView.setSelectedColor(SELECT_COLOR);
        titleView.setTextSize(15);

        if(null != mDataListener) {
            titleView.setText(mDataListener.getTitle(index));
            titleView.setOnClickListener(v -> mDataListener.onIndexClicked(index));
        }

        return titleView;
    }

}
