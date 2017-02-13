package com.sunzn.picker.library.picker.text;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by sunzn on 2017/2/10.
 */

public class TextPickerBoxBuilder {

    private int mScreenWidth;
    private int mScreenHeight;

    private Context context;

    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;

    private String mText;
    private TextPickerBoxListener mListener;

    TextPickerBoxBuilder(Context context) {
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

    public TextPickerBoxBuilder setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public boolean getCancelable() {
        return mCancelable;
    }

    public TextPickerBoxBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public boolean getCanceledOnTouchOutside() {
        return mCanceledOnTouchOutside;
    }

    public TextPickerBoxBuilder setTextPickerBoxListener(TextPickerBoxListener listener) {
        mListener = listener;
        return this;
    }

    public TextPickerBoxListener getTextPickerBoxListener() {
        return mListener;
    }

    public TextPickerBoxBuilder setText(String text) {
        mText = text;
        return this;
    }

    public String getText() {
        return mText == null ? "" : mText;
    }

    public TextPickerBox create() {
        return new TextPickerBox(this, context);
    }

}
