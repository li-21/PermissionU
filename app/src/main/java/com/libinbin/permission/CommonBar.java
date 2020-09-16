package com.libinbin.permission;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * @author LiBinBin
 * @Date : 2020/9/16
 * @Description :
 */
public class CommonBar extends LinearLayout {

    ImageView ivLeft;
    ImageView ivRight;
    TextView tvTitle;
    TextView tvRedPoint;
    TextView tvRight;

    public CommonBar(Context context) {
        this(context, null);
    }

    public CommonBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.common_bar, this, true);
        ivLeft = findViewById(R.id.iv_left);
        ivRight = findViewById(R.id.iv_right);
        tvTitle = findViewById(R.id.tv_title);
        tvRedPoint = findViewById(R.id.tv_red_point);
        tvRight = findViewById(R.id.tv_right);

        TypedArray attributes  = context.obtainStyledAttributes(attrs, R.styleable.CommonBar);
        if (attributes != null){
            Drawable drawableLeft = attributes.getDrawable(R.styleable.CommonBar_iv_left);
            if(drawableLeft != null){
                ivLeft.setImageDrawable(drawableLeft);
            }
            Drawable drawableRight = attributes.getDrawable(R.styleable.CommonBar_iv_right);
            if(drawableRight != null){
                ivRight.setImageDrawable(drawableRight);
            }
            String title = attributes.getString(R.styleable.CommonBar_text_title);
            if(!TextUtils.isEmpty(title)){
                tvTitle.setText(title);
            }

            int titleColor = attributes.getColor(R.styleable.CommonBar_text_title_color, ContextCompat.getColor(context,R.color.color_black));
            tvTitle.setTextColor(titleColor);

            boolean leftVisible = attributes.getBoolean(R.styleable.CommonBar_iv_left_visible, true);

            if (leftVisible){
                int leftPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 15,context.getResources().getDisplayMetrics());
                tvTitle.setPadding(leftPadding,0,0,0);
                ivLeft.setVisibility(View.VISIBLE);
            }else{
                ivLeft.setVisibility(View.GONE);
            }
        }

        attributes .recycle();
    }

    public void setRedPoint(int count){
        if(count > 0){
            tvRedPoint.setText(count + "");
            tvRedPoint.setVisibility(VISIBLE);
        }

    }

    public void hideTvRedPoint(){
        tvRedPoint.setText("");
        tvRedPoint.setVisibility(GONE);
    }


    public void setTvRightStyle(String text,int color){
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(text);
        tvRight.setTextColor(color);
    }


    public void showRedPoint(){
        tvRedPoint.setVisibility(VISIBLE);
    }

    public void dismissRedPoint(){
        tvRedPoint.setVisibility(INVISIBLE);
    }
}
