package com.sunzn.picker.library.picker.mode;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sunzn.picker.library.R;

import java.util.List;

/**
 * Created by sunzn on 2017/2/10.
 */

public class ModePickerBox {

    private Dialog mDialog;
    private Context mContext;
    private final Mode mSelectedBean;
    private final List<Mode> mModeBeans;
    private ModePickerBoxListener mListener;
    private final ModeAdapter mModeAdapter;

    ModePickerBox(ModePickerBoxBuilder builder, Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.picker_mode_holder, null);
        mDialog = new Dialog(context, R.style.ActionBoxStyle);
        mDialog.setContentView(view);
        mDialog.setCancelable(builder.getCancelable());
        mDialog.setCanceledOnTouchOutside(builder.getCanceledOnTouchOutside());

        mContext = context;
        mModeAdapter = new ModeAdapter();
        mModeBeans = builder.getItemBeans();
        mSelectedBean = builder.getSelectedBean();
        mModeAdapter.setModeBeans(mModeBeans);
        mModeAdapter.setSelectBean(mSelectedBean);
        mListener = builder.getModePickerBoxListener();


        Window window = mDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.x = 0;
            lp.y = 0;
            lp.width = builder.getScreenWidth();
            window.setAttributes(lp);
        }

        initModeBoxView(view);

    }

    private void initModeBoxView(View view) {
        if (view != null) {
            ListView modeView = (ListView) view.findViewById(R.id.lv_mode);
            TextView cancelView = (TextView) view.findViewById(R.id.tv_cancel);
            TextView ensureView = (TextView) view.findViewById(R.id.tv_ensure);

            modeView.setAdapter(mModeAdapter);
            modeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mModeAdapter.setSelectBean(mModeBeans.get(position));
                    mModeAdapter.notifyDataSetChanged();
                }
            });

            cancelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCancelClick();
                }
            });

            ensureView.setOnClickListener(new View.OnClickListener() {
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
            mListener.onEnsureClick(mModeAdapter.getSelectBean());
        }
        dismiss();
    }

    public static ModePickerBoxBuilder newBox(Context context) {
        return new ModePickerBoxBuilder(context);
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    private class ModeAdapter extends BaseAdapter {

        private Mode mBean;
        private List<Mode> mBeans;
        private LayoutInflater mInflater;

        ModeAdapter() {
            mInflater = LayoutInflater.from(mContext);
        }

        void setSelectBean(Mode bean) {
            mBean = bean;
        }

        public Mode getSelectBean() {
            return mBean;
        }

        void setModeBeans(List<Mode> beans) {
            mBeans = beans;
        }

        @Override
        public int getCount() {
            return mBeans == null ? 0 : mBeans.size();
        }

        @Override
        public Mode getItem(int position) {
            return mBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.picker_mode_item, null);
                holder.name = (TextView) convertView.findViewById(R.id.mode);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(getItem(position).getMode());

            if (getItem(position).getMode().equals(mBean.getMode())) {
                holder.icon.setVisibility(View.VISIBLE);
            } else {
                holder.icon.setVisibility(View.GONE);
            }

            return convertView;
        }

    }

    private final class ViewHolder {
        public TextView name;
        public ImageView icon;
    }

}
