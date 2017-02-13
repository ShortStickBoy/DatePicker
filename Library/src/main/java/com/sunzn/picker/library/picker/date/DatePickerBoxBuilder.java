package com.sunzn.picker.library.picker.date;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.sunzn.picker.library.config.ScrollerConfig;
import com.sunzn.picker.library.data.Mode;
import com.sunzn.picker.library.data.WheelCalendar;
import com.sunzn.picker.library.listener.OnDateSetListener;

/**
 * Created by sunzn on 2017/2/10.
 */

public class DatePickerBoxBuilder {

    private int mScreenWidth;
    private int mScreenHeight;

    private Context context;

    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;

    private ScrollerConfig mScrollerConfig;

    DatePickerBoxBuilder(Context context) {
        if (context == null) throw new NullPointerException("Context may not be null");
        this.context = context;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        mScrollerConfig = new ScrollerConfig();
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public DatePickerBoxBuilder setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public boolean getCancelable() {
        return mCancelable;
    }

    public DatePickerBoxBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public boolean getCanceledOnTouchOutside() {
        return mCanceledOnTouchOutside;
    }

    public ScrollerConfig getScrollerConfig() {
        return mScrollerConfig;
    }

    public DatePickerBoxBuilder setType(Mode type) {
        mScrollerConfig.mType = type;
        return this;
    }

    public DatePickerBoxBuilder setThemeColor(@ColorRes int color) {
        mScrollerConfig.mToolbarBkgColor = color;
        return this;
    }

    public DatePickerBoxBuilder setCancelStringId(String left) {
        mScrollerConfig.mCancelString = left;
        return this;
    }

    public DatePickerBoxBuilder setSureStringId(String right) {
        mScrollerConfig.mSureString = right;
        return this;
    }

    public DatePickerBoxBuilder setToolBarTextColor(int color) {
        mScrollerConfig.mToolBarTVColor = color;
        return this;
    }

    public DatePickerBoxBuilder setWheelItemTextNormalColor(int color) {
        mScrollerConfig.mWheelTVNormalColor = color;
        return this;
    }

    public DatePickerBoxBuilder setWheelItemTextSelectorColor(int color) {
        mScrollerConfig.mWheelTVSelectorColor = color;
        return this;
    }

    public DatePickerBoxBuilder setWheelItemTextSize(int size) {
        mScrollerConfig.mWheelTVSize = size;
        return this;
    }

    public DatePickerBoxBuilder setCyclic(boolean cyclic) {
        mScrollerConfig.cyclic = cyclic;
        return this;
    }

    public DatePickerBoxBuilder setMinMilliseconds(long milliseconds) {
        mScrollerConfig.mMinCalendar = new WheelCalendar(milliseconds);
        return this;
    }

    public DatePickerBoxBuilder setMaxMilliseconds(long milliseconds) {
        mScrollerConfig.mMaxCalendar = new WheelCalendar(milliseconds);
        return this;
    }

    public DatePickerBoxBuilder setCurMilliseconds(long milliseconds) {
        mScrollerConfig.mCurCalendar = new WheelCalendar(milliseconds);
        return this;
    }

    public DatePickerBoxBuilder setYearText(String year) {
        mScrollerConfig.mYear = year;
        return this;
    }

    public DatePickerBoxBuilder setMonthText(String month) {
        mScrollerConfig.mMonth = month;
        return this;
    }

    public DatePickerBoxBuilder setDayText(String day) {
        mScrollerConfig.mDay = day;
        return this;
    }

    public DatePickerBoxBuilder setHourText(String hour) {
        mScrollerConfig.mHour = hour;
        return this;
    }

    public DatePickerBoxBuilder setMinuteText(String minute) {
        mScrollerConfig.mMinute = minute;
        return this;
    }

    public DatePickerBoxBuilder setCallback(OnDateSetListener listener) {
        mScrollerConfig.mCallback = listener;
        return this;
    }

    public DatePickerBoxBuilder setListener(DatePickerBoxListener listener) {
        mScrollerConfig.mListener = listener;
        return this;
    }

    public DatePickerBox create() {
        return new DatePickerBox(this, context);
    }

}
