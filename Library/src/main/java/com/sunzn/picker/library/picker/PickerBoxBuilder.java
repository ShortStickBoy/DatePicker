package com.sunzn.picker.library.picker;

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

public class PickerBoxBuilder {

    private int mScreenWidth;
    private int mScreenHeight;

    private Context context;

    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;

    private ScrollerConfig mScrollerConfig;

    PickerBoxBuilder(Context context) {
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

    public PickerBoxBuilder setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public boolean getCancelable() {
        return mCancelable;
    }

    public PickerBoxBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public boolean getCanceledOnTouchOutside() {
        return mCanceledOnTouchOutside;
    }

    public ScrollerConfig getScrollerConfig() {
        return mScrollerConfig;
    }

    public PickerBoxBuilder setType(Mode type) {
        mScrollerConfig.mType = type;
        return this;
    }

    public PickerBoxBuilder setThemeColor(@ColorRes int color) {
        mScrollerConfig.mToolbarBkgColor = color;
        return this;
    }

    public PickerBoxBuilder setCancelStringId(String left) {
        mScrollerConfig.mCancelString = left;
        return this;
    }

    public PickerBoxBuilder setSureStringId(String right) {
        mScrollerConfig.mSureString = right;
        return this;
    }

    public PickerBoxBuilder setToolBarTextColor(int color) {
        mScrollerConfig.mToolBarTVColor = color;
        return this;
    }

    public PickerBoxBuilder setWheelItemTextNormalColor(int color) {
        mScrollerConfig.mWheelTVNormalColor = color;
        return this;
    }

    public PickerBoxBuilder setWheelItemTextSelectorColor(int color) {
        mScrollerConfig.mWheelTVSelectorColor = color;
        return this;
    }

    public PickerBoxBuilder setWheelItemTextSize(int size) {
        mScrollerConfig.mWheelTVSize = size;
        return this;
    }

    public PickerBoxBuilder setCyclic(boolean cyclic) {
        mScrollerConfig.cyclic = cyclic;
        return this;
    }

    public PickerBoxBuilder setMinMilliseconds(long milliseconds) {
        mScrollerConfig.mMinCalendar = new WheelCalendar(milliseconds);
        return this;
    }

    public PickerBoxBuilder setMaxMilliseconds(long milliseconds) {
        mScrollerConfig.mMaxCalendar = new WheelCalendar(milliseconds);
        return this;
    }

    public PickerBoxBuilder setCurMilliseconds(long milliseconds) {
        mScrollerConfig.mCurCalendar = new WheelCalendar(milliseconds);
        return this;
    }

    public PickerBoxBuilder setYearText(String year) {
        mScrollerConfig.mYear = year;
        return this;
    }

    public PickerBoxBuilder setMonthText(String month) {
        mScrollerConfig.mMonth = month;
        return this;
    }

    public PickerBoxBuilder setDayText(String day) {
        mScrollerConfig.mDay = day;
        return this;
    }

    public PickerBoxBuilder setHourText(String hour) {
        mScrollerConfig.mHour = hour;
        return this;
    }

    public PickerBoxBuilder setMinuteText(String minute) {
        mScrollerConfig.mMinute = minute;
        return this;
    }

    public PickerBoxBuilder setCallback(OnDateSetListener listener) {
        mScrollerConfig.mCallback = listener;
        return this;
    }

    public PickerBoxBuilder setListener(PickerBoxListener listener) {
        mScrollerConfig.mListener = listener;
        return this;
    }

    public PickerBox create() {
        return new PickerBox(this, context);
    }

}
