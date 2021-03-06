package com.sunzn.picker.library.picker.year;

import android.content.Context;

import java.util.GregorianCalendar;

/**
 * Created by sunzn on 2017/8/30.
 */

public class YearPickBoxUtils {

    public static void showBox(Context context, int minYear, int staYear, int endYear, YearPickBox.ActionListener listener) {
        showBox(context, true, minYear, staYear, endYear, listener);
    }

    public static void showBox(Context context, boolean isCyclic, int minYear, int staYear, int endYear, YearPickBox.ActionListener listener) {
        showBox(context, isCyclic, true, true, new GregorianCalendar(minYear, 0, 1).getTimeInMillis(), System.currentTimeMillis(), staYear, endYear, listener, null);
    }

    public static void showBox(Context context, boolean isCyclic, boolean cancelable, boolean canceledOnTouch, int minYear, int staYear, int endYear, YearPickBox.ActionListener listener) {
        showBox(context, isCyclic, cancelable, canceledOnTouch, new GregorianCalendar(minYear, 0, 1).getTimeInMillis(), System.currentTimeMillis(), staYear, endYear, listener, null);
    }

    public static void showBox(Context context, boolean isCyclic, int minYear, int maxYear, int staYear, int endYear, YearPickBox.ActionListener listener) {
        showBox(context, isCyclic, true, true, new GregorianCalendar(minYear, 0, 1).getTimeInMillis(), new GregorianCalendar(maxYear, 0, 1).getTimeInMillis(), staYear, endYear, listener, null);
    }

    public static void showBox(Context context, boolean isCyclic, int minYear, int maxYear, int staYear, int endYear, YearPickBox.ActionListener listener, YearPickBox.ConfigListener configListener) {
        showBox(context, isCyclic, true, true, new GregorianCalendar(minYear, 0, 1).getTimeInMillis(), new GregorianCalendar(maxYear, 0, 1).getTimeInMillis(), staYear, endYear, listener, configListener);
    }

    /////
    public static void showBox(Context context, boolean isCyclic, boolean cancelable, boolean canceledOnTouch, int minYear, int maxYear, int staYear, int endYear, YearPickBox.ActionListener listener) {
        showBox(context, isCyclic, cancelable, canceledOnTouch, new GregorianCalendar(minYear, 0, 1).getTimeInMillis(), new GregorianCalendar(maxYear, 0, 1).getTimeInMillis(), staYear, endYear, listener, null);
    }

    public static void showBox(Context context, boolean isCyclic, boolean cancelable, boolean canceledOnTouch, int minYear, int maxYear, int staYear, int endYear, YearPickBox.ActionListener listener, YearPickBox.ConfigListener configListener) {
        showBox(context, isCyclic, cancelable, canceledOnTouch, new GregorianCalendar(minYear, 0, 1).getTimeInMillis(), new GregorianCalendar(maxYear, 0, 1).getTimeInMillis(), staYear, endYear, listener, configListener);
    }

    public static void showBox(Context context, boolean isCyclic, boolean cancelable, boolean canceledOnTouch, long minYear, long maxYear, int staYear, int endYear, YearPickBox.ActionListener listener, YearPickBox.ConfigListener configListener) {
        YearPickBox.newBox(context)
                .setCyclic(isCyclic)
                .setCancelable(cancelable)
                .setCanceledOnTouch(false)
                .setMinYear(minYear)
                .setMaxYear(maxYear)
                .setStaYear(staYear)
                .setEndYear(endYear)
                .setActionListener(listener)
                .setConfigListener(configListener)
                .create().show();
    }

}
