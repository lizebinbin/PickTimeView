package com.example.moore.picktimeview.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Moore on 2016/10/19.
 */

public class PickTimeView extends LinearLayout implements NumberPicker.OnValueChangeListener {
    private final String TAG = getClass().getSimpleName();
    private Context mContext;
    /**
     * 视图控件
     */
//    private NumberPicker mNpLeft, mNpMiddle, mNpRight;
    private MyNumberPicker mNpLeft, mNpMiddle, mNpRight;
    private TextView mMinuteTip;
    /**
     * 选择框之间距离（dp）
     */
    private int mOffsetMargin = 12;

    /**
     * 一组数据长度
     */
    private final int DATA_SIZE = 5;

    /**
     * type：选择日期控件
     */
    public static final int TYPE_PICK_DATE = 1;
    /**
     * type：选择时间控件
     */
    public static final int TYPE_PICK_TIME = 2;
    /**
     * 当前type值
     */
    private int mCurrentType = TYPE_PICK_TIME;
    /**
     * 当前时间戳
     */
    private long mTimeMillis;
    /**
     * 选中回调监听
     */
    private onSelectedChangeListener mOnSelectedChangeListener;

    private SimpleDateFormat test;

    public PickTimeView(Context context) {
        super(context);
        this.mContext = context;
        generateView();
        initPicker();
    }

    public PickTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        generateView();
        initPicker();
    }

    public PickTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        generateView();
        initPicker();
    }

    /**
     * 绘制视图
     */
    private void generateView() {
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.setOrientation(VERTICAL);
        this.setGravity(Gravity.CENTER);
        LinearLayout container = new LinearLayout(mContext);
        container.setOrientation(HORIZONTAL);
        container.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        container.setGravity(Gravity.CENTER);
//        mNpLeft = new NumberPicker(mContext);
//        mNpMiddle = new NumberPicker(mContext);
//        mNpRight = new NumberPicker(mContext);
        mNpLeft = new MyNumberPicker(mContext);
        mNpMiddle = new MyNumberPicker(mContext);
        mNpRight = new MyNumberPicker(mContext);
        mMinuteTip = new TextView(mContext);
        mMinuteTip.setText(":");
        mNpLeft.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        mNpMiddle.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        mNpRight.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

//        mNpLeft.setTextColor(Color.BLUE);
//        mNpMiddle.setTextSize(28);
//        mNpRight.setDividerColor(Color.GREEN);
        /**
         * 设置宽高和边距
         */
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        mNpLeft.setLayoutParams(params);
        params.setMargins(dip2px(mOffsetMargin), 0, 0, 0);
        mNpMiddle.setLayoutParams(params);
        mNpRight.setLayoutParams(params);
        mMinuteTip.setLayoutParams(params);

        mNpLeft.setOnValueChangedListener(this);
        mNpMiddle.setOnValueChangedListener(this);
        mNpRight.setOnValueChangedListener(this);

        container.addView(mNpLeft);
        container.addView(mNpMiddle);
        container.addView(mMinuteTip);
        container.addView(mNpRight);
        this.addView(container);
        initTime();
    }

    /**
     * 初始化控件
     */
    private void initPicker() {
        test = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (mCurrentType == TYPE_PICK_DATE) {
            mMinuteTip.setVisibility(GONE);
        } else {
            mMinuteTip.setVisibility(VISIBLE);
        }

        mNpLeft.setMinValue(0);
        mNpLeft.setMaxValue(DATA_SIZE - 1);
        updateLeftValue(mTimeMillis);

        mNpMiddle.setMinValue(0);
        mNpMiddle.setMaxValue(DATA_SIZE - 1);
        updateMiddleValue(mTimeMillis);

        mNpRight.setMinValue(0);
        mNpRight.setMaxValue(DATA_SIZE - 1);
        updateRightValue(mTimeMillis);
    }

    /**
     * 更新左侧控件
     * 日期：选择年控件
     * 时间：选择月份和日期控件
     *
     * @param timeMillis
     */
    private void updateLeftValue(long timeMillis) {
        SimpleDateFormat sdf;
        String str[] = new String[DATA_SIZE];
        if (mCurrentType == TYPE_PICK_DATE) {
            sdf = new SimpleDateFormat("yyyy");
            for (int i = 0; i < DATA_SIZE; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timeMillis);
                cal.add(Calendar.YEAR, (i - DATA_SIZE / 2));
                str[i] = sdf.format(cal.getTimeInMillis());
            }
        } else {
            sdf = new SimpleDateFormat("MM-dd EEE");
            for (int i = 0; i < DATA_SIZE; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timeMillis);
                cal.add(Calendar.DAY_OF_MONTH, (i - DATA_SIZE / 2));
                str[i] = sdf.format(cal.getTimeInMillis());
            }
        }
        mNpLeft.setDisplayedValues(str);
        mNpLeft.setValue(DATA_SIZE / 2);
        mNpLeft.postInvalidate();
    }

    /**
     * 更新中间控件
     * 日期：选择月份控件
     * 时间：选择小时控件
     *
     * @param timeMillis
     */
    private void updateMiddleValue(long timeMillis) {
        SimpleDateFormat sdf;
        String str[] = new String[DATA_SIZE];
        if (mCurrentType == TYPE_PICK_DATE) {
            sdf = new SimpleDateFormat("MM");
            for (int i = 0; i < DATA_SIZE; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timeMillis);
                cal.add(Calendar.MONTH, (i - DATA_SIZE / 2));
                str[i] = sdf.format(cal.getTimeInMillis());
            }
        } else {
            sdf = new SimpleDateFormat("HH");
            for (int i = 0; i < DATA_SIZE; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timeMillis);
                cal.add(Calendar.HOUR_OF_DAY, (i - DATA_SIZE / 2));
                str[i] = sdf.format(cal.getTimeInMillis());
            }
        }
        mNpMiddle.setDisplayedValues(str);
        mNpMiddle.setValue(DATA_SIZE / 2);
        mNpMiddle.postInvalidate();
    }

    /**
     * 更新右侧控件
     * 日期：选择日期控件
     * 时间：选择分钟控件
     *
     * @param timeMillis
     */
    private void updateRightValue(long timeMillis) {
        SimpleDateFormat sdf;
        String str[] = new String[DATA_SIZE];
        if (mCurrentType == TYPE_PICK_DATE) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeMillis);
            int days = getMaxDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            for (int i = 0; i < DATA_SIZE; i++) {
                int temp = currentDay - (DATA_SIZE / 2 - i);
                if (temp > days) {
                    temp -= days;
                }
                if (temp < 1) {
                    temp += days;
                }
                str[i] = temp > 9 ? temp + "" : "0" + temp;
            }
        } else {
            sdf = new SimpleDateFormat("mm");
            for (int i = 0; i < DATA_SIZE; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timeMillis);
                cal.add(Calendar.MINUTE, (i - DATA_SIZE / 2));
                str[i] = sdf.format(cal.getTimeInMillis());
            }
        }
        mNpRight.setDisplayedValues(str);
        mNpRight.setValue(DATA_SIZE / 2);
        mNpRight.postInvalidate();
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mTimeMillis);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int offset = newVal - oldVal;
        if (picker == mNpLeft) {
            if (mCurrentType == TYPE_PICK_DATE) {
                calendar.add(Calendar.YEAR, offset);
            } else {
                calendar.add(Calendar.DAY_OF_MONTH, offset);
            }
            updateLeftValue(calendar.getTimeInMillis());
            mTimeMillis = calendar.getTimeInMillis();
        } else if (picker == mNpMiddle) {
            if (mCurrentType == TYPE_PICK_DATE) {
                calendar.add(Calendar.MONTH, offset);
                if (calendar.get(Calendar.YEAR) != year) {
                    calendar.set(Calendar.YEAR, year);
                }
            } else {
                calendar.add(Calendar.HOUR_OF_DAY, offset);
                if (calendar.get(Calendar.DAY_OF_MONTH) != day) {
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                }
                if (calendar.get(Calendar.MONTH) != month) {
                    calendar.set(Calendar.MONTH, month);
                }
                if (calendar.get(Calendar.YEAR) != year) {
                    calendar.set(Calendar.YEAR, year);
                }
            }
            updateMiddleValue(calendar.getTimeInMillis());
            updateRightValue(calendar.getTimeInMillis());
            mTimeMillis = calendar.getTimeInMillis();
        } else if (picker == mNpRight) {
            if (mCurrentType == TYPE_PICK_DATE) {
                int days = getMaxDayOfMonth(year, month + 1);
                if(day == 1 && offset < 0){
                    calendar.set(Calendar.DAY_OF_MONTH,days);
                }else if(day == days && offset > 0){
                    calendar.set(Calendar.DAY_OF_MONTH,1);
                }else{
                    calendar.add(Calendar.DAY_OF_MONTH, offset);
                }

                if (calendar.get(Calendar.MONTH) != month) {
                    calendar.set(Calendar.MONTH, month);
                }
                if (calendar.get(Calendar.YEAR) != year) {
                    calendar.set(Calendar.YEAR, year);
                }
                Log.e(TAG,"time：：："+test.format(calendar.getTimeInMillis()));
            } else {
                calendar.add(Calendar.MINUTE, offset);
                if (calendar.get(Calendar.HOUR_OF_DAY) != hour) {
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                }
                if (calendar.get(Calendar.DAY_OF_MONTH) != day) {
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                }
                if (calendar.get(Calendar.MONTH) != month) {
                    calendar.set(Calendar.MONTH, month);
                }
                if (calendar.get(Calendar.YEAR) != year) {
                    calendar.set(Calendar.YEAR, year);
                }
            }
            updateRightValue(calendar.getTimeInMillis());
            mTimeMillis = calendar.getTimeInMillis();
        }
        /**
         * 向外部发送当前选中时间
         */
        if (mOnSelectedChangeListener != null) {
            mOnSelectedChangeListener.onSelected(this,mTimeMillis);
        }
        Log.e(TAG, "selected time:" + test.format(mTimeMillis));
    }

    /**
     * 未设置时间默认取当前时间
     */
    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        mTimeMillis = calendar.getTimeInMillis();
    }

    /**
     * 设置初始化时间
     *
     * @param timeMillis
     */
    public void setTimeMillis(long timeMillis) {
        if (timeMillis != 0) {
            this.mTimeMillis = timeMillis;
            initPicker();
            this.postInvalidate();
        } else {
            initTime();
        }
    }

    /**
     * 设置视图类型 年月日控件or时间控件
     *
     * @param type
     */
    public void setViewType(int type) {
        if (type == TYPE_PICK_DATE || type == TYPE_PICK_TIME) {
            this.mCurrentType = type;
        } else {
            this.mCurrentType = TYPE_PICK_TIME;
        }
        initPicker();
    }

    /**
     * 设置选中监听回调
     *
     * @param listener
     */
    public void setOnSelectedChangeListener(onSelectedChangeListener listener) {
        this.mOnSelectedChangeListener = listener;
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    private int dip2px(int dp) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (scale * dp + 0.5f);
    }

    /**
     * 获取某年某月的天数
     *
     * @param year
     * @param month
     * @return
     */
    private int getMaxDayOfMonth(int year, int month) {
        int result = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            result = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            result = 30;
        } else {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                result = 29;
            } else {
                result = 28;
            }
        }
        return result;
    }

    /**
     * 选中回调接口
     */
    public interface onSelectedChangeListener {
        void onSelected(PickTimeView view,long timeMillis);
    }
}
