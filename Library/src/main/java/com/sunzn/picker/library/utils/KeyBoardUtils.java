package com.sunzn.picker.library.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sunzn on 2017/2/28.
 */

public class KeyBoardUtils {

    /**
     * 隐藏键盘
     */
    public static void hideKeyBoard(Activity activity, EditText mEditText) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示键盘
     */
    public static void showKeyBoard(final EditText view) {
        if (view != null) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.showSoftInput(view, 0);
                }
            }, 150);
        }
    }

}
