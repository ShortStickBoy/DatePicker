package com.sunzn.picker.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sunzn.picker.library.DatePickerDialog;
import com.sunzn.picker.library.data.Mode;
import com.sunzn.picker.library.listener.OnDateSetListener;
import com.sunzn.picker.library.picker.PickerBox;
import com.sunzn.picker.library.picker.PickerBoxListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

//    private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.ENGLISH);
    private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.SIMPLIFIED_CHINESE);

    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间

    private TextView tv;

    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePickerDialog timePickerView, long milliseconds) {
            String text = getDateToString1(milliseconds);
            tv.setText(text);
        }
    };

    public String getDateToString1(long time) {
        Date d = new Date(time);
        return format.format(d);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = new GregorianCalendar();
                calendar.set(1960, 0, 1, 0, 0);

                PickerBox box = PickerBox.newBox(MainActivity.this)
                        .setType(Mode.YEAR_MONTH_DAY)
                        .setMinMilliseconds(calendar.getTime().getTime())
                        .setMaxMilliseconds(System.currentTimeMillis())
                        .setCurMilliseconds(mLastTime)
                        .setCallback(mOnDateSetListener)
                        .setListener(new PickerBoxListener() {
                            @Override
                            public void onEnsureClick(long timeMillis) {
                                String text = getDateToString1(timeMillis);
                                tv.setText(text);
                            }
                        })
                        .create();
                box.show();

            }
        });


//        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Calendar calendar = new GregorianCalendar();
//                calendar.set(1960, 0, 1, 0, 0);
//
//                // 出生日期
//                DatePickerDialog dialog = new DatePickerDialog.Builder()
//                        .setType(Mode.YEAR_MONTH_DAY)
//                        .setMinMilliseconds(calendar.getTime().getTime())
//                        .setMaxMilliseconds(System.currentTimeMillis())
//                        .setCurMilliseconds(mLastTime)
//                        .setCallback(mOnDateSetListener)
//                        .build();
//                if (dialog != null) {
//                    if (!dialog.isAdded()) {
//                        dialog.show(getSupportFragmentManager(), "year_month_day");
//                    }
//                }
//
//            }
//        });
    }
}
