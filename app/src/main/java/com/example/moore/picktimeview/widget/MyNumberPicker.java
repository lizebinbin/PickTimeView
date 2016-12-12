package com.example.moore.picktimeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

/**
 * Created by Moore on 2016/10/20.
 */

public class MyNumberPicker extends NumberPicker {
    private Context mContext;
    NumberPicker picker;

    public MyNumberPicker(Context context) {
        super(context);
        picker = this;
        mContext = context;
    }

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        picker = this;
        mContext = context;
    }

    public MyNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        picker = this;
        mContext = context;
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
            ((EditText) view).setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            ((EditText) view).setTextColor(Color.parseColor("#6495ED"));
        }
    }

    private int mRight, mLeft, mBottom;
    private int mTopSelectionDividerTop;
    private int mBottomSelectionDividerBottom;
    private int[] mSelectorIndices;
    private int mScrollState;
    private SparseArray<String> mSelectorIndexToStringCache;
    private EditText mInputText;
    private Paint mSelectorWheelPaint;
    private int mSelectorElementHeight;
    private int mCurrentScrollOffset;
    private boolean mHasSelectorWheel;
    private boolean mHideWheelUntilFocused;
    private Drawable mSelectionDivider;

    /**
     * 通过反射获取值
     */
    private void getMyValue() {
        mLeft = super.getLeft();
        mRight = super.getRight();
        mBottom = super.getBottom();
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mSelectorWheelPaint")) {
                try {
                    mSelectorWheelPaint = (Paint) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mSelectorElementHeight")) {
                try {
                    mSelectorElementHeight = (int) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mCurrentScrollOffset")) {
                try {
                    mCurrentScrollOffset = (int) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mInputText")) {
                try {
                    mInputText = (EditText) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mSelectorIndexToStringCache")) {
                try {
                    mSelectorIndexToStringCache = (SparseArray<String>) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mSelectorIndices")) {
                try {
                    mSelectorIndices = (int[]) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mHasSelectorWheel")) {
                try {
                    mHasSelectorWheel = (boolean) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mHideWheelUntilFocused")) {
                try {
                    mHideWheelUntilFocused = (boolean) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
//        for (Field field : pickerFields) {
//            field.setAccessible(true);
//            if (field.getName().equals("mDecrementVirtualButtonPressed")) {
//                try {
//                    mDecrementVirtualButtonPressed = (boolean) field.get(picker);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
//        for (Field field : pickerFields) {
//            field.setAccessible(true);
//            if (field.getName().equals("mIncrementVirtualButtonPressed")) {
//                try {
//                    mIncrementVirtualButtonPressed = (boolean) field.get(picker);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
//        for (Field field : pickerFields) {
//            field.setAccessible(true);
//            if (field.getName().equals("mVirtualButtonPressedDrawable")) {
//                try {
//                    mVirtualButtonPressedDrawable = (Drawable) field.get(picker);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mScrollState")) {
                try {
                    mScrollState = (int) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mTopSelectionDividerTop")) {
                try {
                    mTopSelectionDividerTop = (int) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mBottomSelectionDividerBottom")) {
                try {
                    mBottomSelectionDividerBottom = (int) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        for (Field field : pickerFields) {
            field.setAccessible(true);
            if (field.getName().equals("mSelectionDivider")) {
                try {
                    mSelectionDivider = (Drawable) field.get(picker);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        getMyValue();
        mSelectorWheelPaint.setColor(Color.BLUE);

        if (!mHasSelectorWheel) {
            super.onDraw(canvas);
            return;
        }
        final boolean showSelectorWheel = mHideWheelUntilFocused ? hasFocus() : true;
        float x = (mRight - mLeft) / 2;
        float y = mCurrentScrollOffset;

//        if (showSelectorWheel && mVirtualButtonPressedDrawable != null
//                && mScrollState == OnScrollListener.SCROLL_STATE_IDLE) {
//            if (mDecrementVirtualButtonPressed) {
//                mVirtualButtonPressedDrawable.setState(View.PRESSED_STATE_SET);
//                mVirtualButtonPressedDrawable.setBounds(0, 0, mRight, mTopSelectionDividerTop);
//                mVirtualButtonPressedDrawable.draw(canvas);
//            }
//            if (mIncrementVirtualButtonPressed) {
//                mVirtualButtonPressedDrawable.setState(PRESSED_STATE_SET);
//                mVirtualButtonPressedDrawable.setBounds(0, mBottomSelectionDividerBottom, mRight,
//                        mBottom);
//                mVirtualButtonPressedDrawable.draw(canvas);
//            }
//        }

        int[] selectorIndices = mSelectorIndices;
        for (int i = 0; i < selectorIndices.length; i++) {
            int selectorIndex = selectorIndices[i];
            String scrollSelectorValue = mSelectorIndexToStringCache.get(selectorIndex);
            if (i != 1) {
                mSelectorWheelPaint.setColor(Color.BLACK);
                mSelectorWheelPaint.setTextSize(sp2px(16));
            } else {
                mSelectorWheelPaint.setColor(Color.parseColor("#6495ED"));
                mSelectorWheelPaint.setTextSize(sp2px(20));
            }

            if ((showSelectorWheel && i != 1) ||
                    (i == 1 && mInputText.getVisibility() != VISIBLE)) {
                Rect mRect = new Rect();
                mSelectorWheelPaint.getTextBounds(scrollSelectorValue, 0, scrollSelectorValue.length(), mRect);
                canvas.drawText(scrollSelectorValue, x, y, mSelectorWheelPaint);
            }
            y += mSelectorElementHeight;
        }

        // draw the selection dividers
        if (showSelectorWheel && mSelectionDivider != null) {
            mSelectionDivider = new ColorDrawable(Color.parseColor("#a0c4c4c4"));
            // draw the top divider
            int topOfTopDivider = mTopSelectionDividerTop;
            int bottomOfTopDivider = topOfTopDivider + 2;
            mSelectionDivider.setBounds(0, topOfTopDivider, mRight, bottomOfTopDivider);
            mSelectionDivider.draw(canvas);

            // draw the bottom divider
            int bottomOfBottomDivider = mBottomSelectionDividerBottom;
            int topOfBottomDivider = bottomOfBottomDivider - 2;
            mSelectionDivider.setBounds(0, topOfBottomDivider, mRight, bottomOfBottomDivider);
            mSelectionDivider.draw(canvas);
        }
    }

    private int sp2px(int sp){
        float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (scale*sp+0.5f);
    }
}
