package com.sunzn.picker.library.picker.year;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sunzn.picker.library.R;
import com.sunzn.picker.library.config.ScrollerConfig;
import com.sunzn.picker.library.data.WheelCalendar;
import com.sunzn.picker.library.picker.base.ActionBox;

/**
 * Created by sunzn on 2017/8/30.
 */

public class YearPickBox extends ActionBox {

    public interface ActionListener {

        void onEnsure(int startYear, int endYear);

        void onCancel();

    }

    private YearWheel mYearWheel;

    private ActionListener mActionListener;

    private ScrollerConfig mScrollerConfig;

    public static YearPickBox newBox(Context context) {
        return new YearPickBox(context, R.layout.picker_year_holder);
    }

    public YearPickBox(Context context, int resource) {
        super(context, resource);
        mScrollerConfig = new ScrollerConfig();
    }

    public YearPickBox setActionListener(ActionListener listener) {
        this.mActionListener = listener;
        return this;
    }

    @Override
    public void onActionBoxCreated() {
        mYearWheel = new YearWheel(getRootView(), mScrollerConfig);
        TextView cancel = (TextView) findViewById(R.id.tv_cancel);
        TextView ensure = (TextView) findViewById(R.id.tv_ensure);
        initActionView(cancel, ensure);
    }

    public YearPickBox setCyclic(boolean isCyclic) {
        this.mScrollerConfig.cyclic = isCyclic;
        return this;
    }

    public YearPickBox setMinYear(long milliseconds) {
        this.mScrollerConfig.mMinCalendar = new WheelCalendar(milliseconds);
        return this;
    }

    public YearPickBox setMaxYear(long milliseconds) {
        this.mScrollerConfig.mMaxCalendar = new WheelCalendar(milliseconds);
        return this;
    }

    public YearPickBox setStaYear(int year) {
        this.mScrollerConfig.mStaYear = year;
        return this;
    }

    public YearPickBox setEndYear(int year) {
        this.mScrollerConfig.mEndYear = year;
        return this;
    }

    private void initActionView(TextView cancel, TextView ensure) {
        if (cancel != null) {
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fade();
                    if (mActionListener != null) mActionListener.onCancel();
                }
            });
        }
        if (ensure != null) {
            ensure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fade();
                    if (mActionListener != null && mYearWheel != null) {
                        mActionListener.onEnsure(mYearWheel.getCurrentStaYear(), mYearWheel.getCurrentEndYear());
                    }
                }
            });
        }
    }


}
