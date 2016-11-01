package com.example.moore.picktimeview.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

/**
 * Created by Moore on 2016/10/20.
 */

public class MyNumberPicker extends NumberPicker {
    private static int mTextSize = 16;
    private static int mTextColor = 0x000000;
    private static int mDividerColor = 0xFFFF00;

    public MyNumberPicker(Context context) {
        super(context);
        setNumberPickerDividerColor();
    }

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNumberPickerDividerColor();
    }

    public MyNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setNumberPickerDividerColor();
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    private void updateView(View view) {
        if (view instanceof EditText) {
//            ((EditText) view).setTextSize(mTextSize);
            ((EditText) view).setTextSize(17);
//            ((EditText) view).setTextColor(mTextColor);
            ((EditText) view).setTextColor(Color.parseColor("#6495ED"));
        }
    }

    private void setNumberPickerDividerColor() {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        /**
         * 设置分割线颜色
         */
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
//                    pf.set(this, new ColorDrawable(mDividerColor));
                    pf.set(this, new ColorDrawable(Color.parseColor("#C4C4C4")));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        /**
         * 设置分割线高度
         */
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDividerHeight")) {
                pf.setAccessible(true);
                try {
                    pf.set(this, 2);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectorElementHeight")) {
                pf.setAccessible(true);
                try {
                    pf.set(this, 2);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void setDividerColor(int color) {
        this.mDividerColor = color;
//        this.postInvalidate();
    }

    public void setTextColor(int color) {
        this.mTextColor = color;
//        this.postInvalidate();
    }

    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
//        this.postInvalidate();
    }
}
