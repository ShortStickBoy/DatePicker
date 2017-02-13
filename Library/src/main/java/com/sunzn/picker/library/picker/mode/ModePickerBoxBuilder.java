package com.sunzn.picker.library.picker.mode;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.List;

/**
 * Created by sunzn on 2017/2/10.
 */

public class ModePickerBoxBuilder {

    private int mScreenWidth;
    private int mScreenHeight;

    private Context context;

    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;

    private int mShowMode;
    private ModePickerBoxListener mListener;

    private Mode mSelectedBean;

    private List<Mode> mItemBeans;

    ModePickerBoxBuilder(Context context) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        this.context = context;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public ModePickerBoxBuilder setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public boolean getCancelable() {
        return mCancelable;
    }

    public ModePickerBoxBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public boolean getCanceledOnTouchOutside() {
        return mCanceledOnTouchOutside;
    }

    public ModePickerBoxBuilder setModePickerBoxListener(ModePickerBoxListener listener) {
        mListener = listener;
        return this;
    }

    public ModePickerBoxListener getModePickerBoxListener() {
        return mListener;
    }

    public ModePickerBoxBuilder setSelected(Mode bean) {
        mSelectedBean = bean;
        return this;
    }

    public Mode getSelectedBean() {
        return mSelectedBean;
    }

    public ModePickerBoxBuilder setItemBeans(List<Mode> beans) {
        mItemBeans = beans;
        return this;
    }

    public List<Mode> getItemBeans() {
        return mItemBeans;
    }

    public ModePickerBoxBuilder setShowMode(int mode) {
        mShowMode = mode;
        return this;
    }

    public int getShowMode() {
        return mShowMode;
    }

    public ModePickerBox create() {
        return new ModePickerBox(this, context);
    }

}
