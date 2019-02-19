package com.sunzn.picker.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sunzn.picker.library.DatePickerDialog;
import com.sunzn.picker.library.listener.OnDateSetListener;
import com.sunzn.picker.library.picker.text.TextPickerBox;
import com.sunzn.picker.library.picker.text.TextPickerBoxListener;
import com.sunzn.picker.library.picker.year.YearPickBox;
import com.sunzn.picker.library.picker.year.YearPickBoxUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

//        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                YearPickBoxUtils.showBox(MainActivity.this, 1995, 2000, 2005, new YearPickBox.ActionListener() {
//                    @Override
//                    public void onEnsure(int startYear, int endYear) {
//                        tv.setText("时间：" + startYear + " - " + endYear);
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//                });
//
//            }
//        });

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextPickerBox box = TextPickerBox.newBox(MainActivity.this)
                        .setText("0")
                        .setTextPickerBoxListener(new TextPickerBoxListener() {
                            @Override
                            public void onEnsureClick(float money) {
                                tv.setText(money + "元");
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
//                List<com.sunzn.picker.library.picker.mode.Mode> sources = new ArrayList<>();
//                sources.add(new Source("支付宝"));
//                sources.add(new Source("微信"));
//                sources.add(new Source("知网卡"));
//
//                ModePickerBox box = ModePickerBox.newBox(MainActivity.this)
//                        .setSelected(new Source("支付宝"))
//                        .setItemBeans(sources)
//                        .setModePickerBoxListener(new ModePickerBoxListener() {
//                            @Override
//                            public void onEnsureClick(Mode mode) {
//                                tv.setText(mode.getMode());
//                            }
//                        })
//                        .create();
//                box.show();
//
//            }
//        });

//        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Calendar calendar = new GregorianCalendar();
//                calendar.set(1960, 0, 1, 0, 0);
//
//                DatePickerBox box = DatePickerBox.newBox(MainActivity.this)
//                        .setType(Mode.YEAR_MONTH_DAY)
//                        .setMinMilliseconds(calendar.getTime().getTime())
//                        .setMaxMilliseconds(System.currentTimeMillis())
//                        .setCurMilliseconds(mLastTime)
//                        .setCallback(mOnDateSetListener)
//                        .setListener(new DatePickerBoxListener() {
//                            @Override
//                            public void onEnsureClick(long timeMillis) {
//                                String text = getDateToString1(timeMillis);
//                                tv.setText(text);
//                            }
//                        })
//                        .create();
//                box.show();
//
//            }
//        });


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
