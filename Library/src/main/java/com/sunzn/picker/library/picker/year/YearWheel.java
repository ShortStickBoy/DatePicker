package com.sunzn.picker.library.picker.year;

import android.content.Context;
import android.view.View;

import com.sunzn.picker.library.R;
import com.sunzn.picker.library.adapter.NumericWheelAdapter;
import com.sunzn.picker.library.config.ScrollerConfig;
import com.sunzn.picker.library.picker.year.port.YearRepository;
import com.sunzn.picker.library.utils.DateConstants;
import com.sunzn.picker.library.wheel.OnWheelChangedListener;
import com.sunzn.picker.library.wheel.WheelView;

/**
 * Created by sunzn on 2017/8/30.
 */

public class YearWheel {

    private Context mContext;

    private WheelView mStaYearView, mEndYearView;

    private ScrollerConfig mScrollerConfig;
    private YearRepository mYearRepository;

    private OnWheelChangedListener mStaYearListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateEndYear();
        }
    };

    private OnWheelChangedListener mEndYearListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateStaYear();
        }
    };

    public YearWheel(View view, ScrollerConfig scrollerConfig) {
        mContext = view.getContext();
        mScrollerConfig = scrollerConfig;
        mYearRepository = new YearRepository(scrollerConfig);
        initView(view);
    }

    private void initView(View view) {
        initStaYear(view);
        initEndYear(view);
    }

    private void initStaYear(View view) {
        mStaYearView = view.findViewById(R.id.sta_year);
        mStaYearView.addChangingListener(mStaYearListener);

        int minYear = mYearRepository.getMinYear();
        int maxYear = mYearRepository.getMaxYear();

        NumericWheelAdapter mStaYearAdapter = new NumericWheelAdapter(mContext, minYear, maxYear, DateConstants.FORMAT, mScrollerConfig.mYear);
        mStaYearAdapter.setConfig(mScrollerConfig);
        mStaYearView.setViewAdapter(mStaYearAdapter);
        mStaYearView.setCyclic(mScrollerConfig.cyclic);
        mStaYearView.setCurrentItem(mYearRepository.getStaYear() - minYear);
    }

    private void initEndYear(View view) {
        mEndYearView = view.findViewById(R.id.end_year);
        mEndYearView.addChangingListener(mEndYearListener);

        int minYear = mYearRepository.getMinYear();
        int maxYear = mYearRepository.getMaxYear();

        NumericWheelAdapter mEndYearAdapter = new NumericWheelAdapter(mContext, minYear, maxYear, DateConstants.FORMAT, mScrollerConfig.mYear);
        mEndYearAdapter.setConfig(mScrollerConfig);
        mEndYearView.setViewAdapter(mEndYearAdapter);
        mEndYearView.setCyclic(mScrollerConfig.cyclic);
        mEndYearView.setCurrentItem(mYearRepository.getEndYear() - minYear);
    }

    private void updateEndYear() {
        if (mEndYearView != null && mStaYearView != null && getCurrentStaYear() > getCurrentEndYear()) {
            mEndYearView.setCurrentItem(mStaYearView.getCurrentItem());
        }
    }

    private void updateStaYear() {
        if (mEndYearView != null && mStaYearView != null && getCurrentStaYear() > getCurrentEndYear()) {
            mStaYearView.setCurrentItem(mEndYearView.getCurrentItem());
        }
    }

    public int getCurrentStaYear() {
        return mStaYearView.getCurrentItem() + mYearRepository.getMinYear();
    }

    public int getCurrentEndYear() {
        return mEndYearView.getCurrentItem() + mYearRepository.getMinYear();
    }

}
