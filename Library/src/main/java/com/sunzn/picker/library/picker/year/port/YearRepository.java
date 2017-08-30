package com.sunzn.picker.library.picker.year.port;

import com.sunzn.picker.library.config.ScrollerConfig;
import com.sunzn.picker.library.data.WheelCalendar;
import com.sunzn.picker.library.utils.DateConstants;
import com.sunzn.picker.library.utils.Utils;

public class YearRepository implements YearDataSource {

    private ScrollerConfig mScrollerConfig;

    private WheelCalendar mCalendarMin, mCalendarMax;

    private boolean mIsMinNoRange, mIsMaxNoRange;

    public YearRepository(ScrollerConfig config) {
        mScrollerConfig = config;
        mCalendarMin = mScrollerConfig.mMinCalendar;
        mCalendarMax = mScrollerConfig.mMaxCalendar;

        mIsMinNoRange = mCalendarMin.isNoRange();
        mIsMaxNoRange = mCalendarMax.isNoRange();
    }

    @Override
    public int getMinYear() {
        if (mIsMinNoRange)
            return DateConstants.DEFAULT_MIN_YEAR;
        else
            return mCalendarMin.year;
    }

    @Override
    public int getMaxYear() {
        if (mIsMaxNoRange) {
            return getMinYear() + DateConstants.YEAR_COUNT;
        }
        return mCalendarMax.year;
    }

    @Override
    public int getStaYear() {
        if (mScrollerConfig.mStaYear == 0) {
            return getDefaultCalendar().year;
        }
        return mScrollerConfig.mStaYear;
    }

    @Override
    public int getEndYear() {
        if (mScrollerConfig.mEndYear == 0) {
            return getDefaultCalendar().year;
        }
        return mScrollerConfig.mEndYear;
    }

    @Override
    public boolean isMinYear(int year) {
        return Utils.isTimeEquals(mCalendarMin, year);
    }

    @Override
    public WheelCalendar getDefaultCalendar() {
        return mScrollerConfig.mCurCalendar;
    }
}
