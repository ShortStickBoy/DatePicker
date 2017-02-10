package com.sunzn.picker.library.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * 抽象滚轮适配器
 */
abstract class AbstractWheelAdapter implements WheelViewAdapter {

    private List<DataSetObserver> mDataSetObservers; // 数据集观察者

    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        if (mDataSetObservers == null) {
            mDataSetObservers = new LinkedList<>();
        }
        mDataSetObservers.add(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (mDataSetObservers != null) {
            mDataSetObservers.remove(observer);
        }
    }

    /**
     * 通知观察者数据改变
     */
    protected void notifyDataChangedEvent() {
        if (mDataSetObservers != null) {
            for (DataSetObserver observer : mDataSetObservers) {
                observer.onChanged();
            }
        }
    }

    /**
     * 通知观察者无效数据
     */
    protected void notifyDataInvalidatedEvent() {
        if (mDataSetObservers != null) {
            for (DataSetObserver observer : mDataSetObservers) {
                observer.onInvalidated();
            }
        }
    }

}
