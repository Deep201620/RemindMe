package com.example.remindme;

import android.provider.BaseColumns;

public final class AlarmSchema {

    private AlarmSchema() {
    }

    //Declared and defined some constant to be used frequently in our project
    public static class Alarm_Colns implements BaseColumns {
        public static final String TABLE_NAME = "AlarmDb";
        public static final String COL_Alarm_Id = "AlarmId";
        public static final String Col_Alarm_Hour = "AlarmHour";
        public static final String Col_Alarm_Minute = "AlarmMinutes";
        public static final String Col_Alarm_Name = "AlarmName";
        public static final String Col_meridiem = "AlarmMeridiem";
        public static final String Col_alarmStatus = "AlarmStatus";



    }

}
