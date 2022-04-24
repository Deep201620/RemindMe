package com.example.remindme;

public class AlarmData {

    String alarmName;
    String alarmHour;
    String alarmMinutes;
    String alarmMeridiem;
    int alarmOnOff;

    public int getAlarmOnOff() {
        return alarmOnOff;
    }

    public void setAlarmOnOff(int alarmOnOff) {
        this.alarmOnOff = alarmOnOff;
    }


    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getAlarmHour() {
        return alarmHour;
    }

    public void setAlarmHour(String alarmHour) {
        this.alarmHour = alarmHour;
    }

    public String getAlarmMinutes() {
        return alarmMinutes;
    }

    public void setAlarmMinutes(String alarmMinutes) {
        this.alarmMinutes = alarmMinutes;
    }

    public String getAlarmMeridiem() {
        return alarmMeridiem;
    }

    public void setAlarmMeridiem(String alarmMeridiem) {
        this.alarmMeridiem = alarmMeridiem;
    }

}
