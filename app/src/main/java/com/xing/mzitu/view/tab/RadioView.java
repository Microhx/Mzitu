package com.xing.mzitu.view.tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.xing.mzitu.R;
import com.xing.mzitu.utils.UIUtils;


/**
 * Created by xinghe on 20/03/2018.
 *
 * 自定义顶部RadioButton
 */

public class RadioView extends RelativeLayout implements SelectInterface{

    TextView tvTitle ;

    ImageView ivTitle ;

    public RadioView(Context context) {
        this(context,null);
    }

    public RadioView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setGravity(Gravity.CENTER);
        setMinimumWidth(UIUtils.dp2px(56));

        initViews();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RadioView);
        int headRes = array.getResourceId(R.styleable.RadioView_radio_view_res,0);
        if(headRes > 0) {
            ivTitle.setImageResource(headRes);
        }

        int titleRes = array.getResourceId(R.styleable.RadioView_radio_view_title,0);
        if(titleRes > 0) {
            tvTitle.setText(titleRes);
        }

        int numCount = array.getInt(R.styleable.RadioView_radio_view_count,0);
        setUnReadCount(numCount);
        
        array.recycle();
    }

    private void initViews() {
        View rootView = View.inflate(getContext(), R.layout.radio_view_layout,this);
        tvTitle = rootView.findViewById(R.id.id_tv_content);
        ivTitle = rootView.findViewById(R.id.id_iv_image);
    }

    public void setData(@StringRes int title , @DrawableRes int res){
        tvTitle.setText(title);
        ivTitle.setImageResource(res);
    }

    @Override
    public void onSelected(boolean selected) {
        tvTitle.setSelected(selected);
        ivTitle.setSelected(selected);
    }

    @Override
    public void setUnReadCount(int count) {}

}
