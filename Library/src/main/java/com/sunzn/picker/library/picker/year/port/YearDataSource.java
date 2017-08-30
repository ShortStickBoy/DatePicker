package com.sunzn.picker.library.picker.year.port;


import com.sunzn.picker.library.data.WheelCalendar;

public interface YearDataSource {

    int getMinYear();

    int getMaxYear();

    int getStaYear();

    int getEndYear();

    boolean isMinYear(int year);

    WheelCalendar getDefaultCalendar();
}
