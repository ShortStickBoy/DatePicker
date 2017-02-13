package com.sunzn.picker.library.picker.date;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sunzn.picker.library.R;
import com.sunzn.picker.library.TimeWheel;
import com.sunzn.picker.library.config.ScrollerConfig;

import java.util.Calendar;

/**
 * Created by sunzn on 2017/2/10.
 */

public class DatePickerBox {

    private Dialog mDialog;
    private TimeWheel mTimeWheel;
    private DatePickerBoxListener mListener;

    DatePickerBox(DatePickerBoxBuilder builder, Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.picker_time_holder, null);
        mDialog = new Dialog(context, R.style.ActionBoxStyle);
        mDialog.setContentView(view);
        mDialog.setCancelable(builder.getCancelable());
        mDialog.setCanceledOnTouchOutside(builder.getCanceledOnTouchOutside());
        ScrollerConfig mScrollerConfig = builder.getScrollerConfig();
        mListener = mScrollerConfig.mListener;
        mTimeWheel = new TimeWheel(view, mScrollerConfig);

        Window window = mDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.x = 0;
            lp.y = 0;
            lp.width = builder.getScreenWidth();
            window.setAttributes(lp);
        }

        initPickerBoxView(view);
    }

    private void initPickerBoxView(View view) {
        if (view != null) {
            view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCancelClick();
                }
            });
            view.findViewById(R.id.tv_ensure).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEnsureClick();
                }
            });
        }
    }

    private void onCancelClick() {
        dismiss();
    }

    private void onEnsureClick() {
        if (mListener != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, mTimeWheel.getCurrentYear());
            calendar.set(Calendar.MONTH, mTimeWheel.getCurrentMonth() - 1);
            calendar.set(Calendar.DAY_OF_MONTH, mTimeWheel.getCurrentDay());
            calendar.set(Calendar.HOUR_OF_DAY, mTimeWheel.getCurrentHour());
            calendar.set(Calendar.MINUTE, mTimeWheel.getCurrentMinute());
            long timeMillis = calendar.getTimeInMillis();
            mListener.onEnsureClick(timeMillis);
        }
        dismiss();
    }

    public void show() {
        if (mDialog != null) mDialog.show();
    }

    private void dismiss() {
        if (mDialog != null) mDialog.dismiss();
    }

    public static DatePickerBoxBuilder newBox(Context context) {
        return new DatePickerBoxBuilder(context);
    }

}
