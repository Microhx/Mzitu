package com.xing.mzitu.view.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.xing.mzitu.R;


/**
 * Created by xinghe on 20/03/2018.
 */

public class RadioLayout extends LinearLayout implements View.OnClickListener {

    /**
     * 首页
     */
    public static final int MAIN_PAGE_INDEX         = 0 ;

    /**
     * 分类
     */
    public static final int CATEGORY_PAGE_INDEX    = 1 ;

    /**
     * 足迹
     */
    public static final int FOOTPRINT_PAGE_INDEX   = 2 ;

    /**
     * 个人
     */
    public static final int MINE_PAGE_INDEX       = 3 ;


    private SparseArray<SelectInterface> cacheArray = new SparseArray<>();

    private int mCurrentIndex = -1 ;

    private onRadioButtonClickListener radioButtonClickListener;

    private static final int[] TITLE_RES = { R.drawable.base_tab_project } ;

    private static final int[] TITLES = {
                                        R.string.main_page,
                                        R.string.category_page,
                                        R.string.footprint_page,
                                        R.string.mine_page
    } ;


    public RadioLayout(Context context) {
        this(context,null);
    }

    public RadioLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadioLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        initData();
    }

    private void initData() {
        removeAllViews();
        addItems(MAIN_PAGE_INDEX,               TITLES[0],       TITLE_RES[0]);
        addItems(CATEGORY_PAGE_INDEX,           TITLES[1],       TITLE_RES[0]);
        addItems(FOOTPRINT_PAGE_INDEX,          TITLES[2],       TITLE_RES[0]);
        addItems(MINE_PAGE_INDEX,               TITLES[3],       TITLE_RES[0]);
    }

    public void addItems(int index , @StringRes int title , @DrawableRes int drawable) {
        RadioView view = new RadioView(getContext());

        view.setTag(index);
        view.onSelected(getChildCount() == 0);
        view.setData(title, drawable);
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        params.gravity = Gravity.CENTER;

        view.setOnClickListener(this);
        addView(view,params);

        cacheArray.put(index,view);
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        changeChecked(index);

        if(mCurrentIndex != index) {
            mCurrentIndex = index;
            if(null != radioButtonClickListener) radioButtonClickListener.onRadioButtonClick(getRealPosition(index));
        }
    }

    public void setIndex(int index) {
        int realPosition = (int) getChildAt(index).getTag();
        if(mCurrentIndex != realPosition) {

            changeChecked(realPosition);
            mCurrentIndex = realPosition;
            if(null != radioButtonClickListener) radioButtonClickListener.onRadioButtonClick(getRealPosition(index));
        }
    }


    public void setRadioButtonClickListener(onRadioButtonClickListener radioButtonClickListener) {
        this.radioButtonClickListener = radioButtonClickListener;
    }

    public interface onRadioButtonClickListener{
        void onRadioButtonClick(int index) ;
    }

    public void resetLayout(){
        removeAllViews();
        mCurrentIndex = -1;
        cacheArray.clear();
    }


    private int getRealPosition(int index){
        for (int i = 0, len = getChildCount(); i < len ; i++) {
            int realIndex = (int) getChildAt(i).getTag();
            if(realIndex == index){
                return i;
            }
        }
        return 0;
    }

    public void changeChecked(int index) {
        for(int i = 0 , size = cacheArray.size() ; i < size ; i++ ){
            int newIndex =  cacheArray.keyAt(i);
            cacheArray.get(newIndex).onSelected(newIndex == index);
        }
    }

}

/**
 *
     if(isSupplier){  //为供应商
     addItems(PROJECT_INDEX , TITLES[4], TITLERES[0]);
     addItems(CHAT_INDEX , TITLES[1], TITLERES[1]);
     addItems(WORK_INDEX , TITLES[2], TITLERES[2]);
     addItems(MINE_INDEX , TITLES[3], TITLERES[3]);

     }else {  //非供应商 工人：非工人

     if (isWorker) {

     } else {
     addItems(PROJECT_INDEX, TITLES[0], TITLERES[0]);
     addItems(CHAT_INDEX, TITLES[1], TITLERES[1]);
     addItems(WORK_INDEX, TITLES[2], TITLERES[2]);
     addItems(MINE_INDEX, TITLES[3], TITLERES[3]);
     }
     }
 *
 */
