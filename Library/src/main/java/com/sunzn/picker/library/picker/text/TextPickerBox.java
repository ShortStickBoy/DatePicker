package com.sunzn.picker.library.picker.text;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.sunzn.picker.library.R;
import com.sunzn.picker.library.utils.KeyBoardUtils;

/**
 * Created by sunzn on 2017/2/10.
 */

public class TextPickerBox {

    private Dialog mDialog;
    private Context mContext;
    private final String mText;
    private EditText mInputView;
    private TextPickerBoxListener mListener;

    TextPickerBox(TextPickerBoxBuilder builder, Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.picker_text_holder, null);
        mDialog = new Dialog(context, R.style.ActionBoxStyleLong);
        mDialog.setContentView(view);
        mDialog.setCancelable(builder.getCancelable());
        mDialog.setCanceledOnTouchOutside(builder.getCanceledOnTouchOutside());

        mContext = context;
        mText = builder.getText();
        mListener = builder.getTextPickerBoxListener();


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
            mInputView = view.findViewById(R.id.et_text);
            mInputView.addTextChangedListener(new DecimalInputTextWatcher(mInputView, 7, 2));//限制输入位数：整数7位，小数点后两位
            mInputView.setText(mText);
            mInputView.setSelection(mText.length());

            TextView cancelView = view.findViewById(R.id.tv_cancel);
            TextView ensureView = view.findViewById(R.id.tv_ensure);

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
            mListener.onEnsureClick(getMoney());
        }
        dismiss();
    }

    private float getMoney() {
        if (mInputView != null && mInputView.getText().toString().length() > 0) {
            return Float.valueOf(mInputView.getText().toString()) >= Integer.MAX_VALUE ? 0 : Float.valueOf(mInputView.getText().toString());
        } else {
            return 0;
        }
    }

    public static TextPickerBoxBuilder newBox(Context context) {
        return new TextPickerBoxBuilder(context);
    }

    public void show() {
        if (mDialog != null && mInputView != null) {
            mDialog.show();
            KeyBoardUtils.showKeyBoard(mInputView);
        }
    }

    public void dismiss() {
        if (mDialog != null && mInputView != null && mContext != null) {
            KeyBoardUtils.hideKeyBoard((Activity) mContext, mInputView);
            mDialog.dismiss();
        }
    }

}
