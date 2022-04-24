package com.example.remindme;

public class Alarms
{
    // Variable to store data corresponding
    // to alarms keyword in database
    private String Alarm1;
    // Mandatory empty constructor
    // for use of FirebaseUI
    public Alarms() {}

    // Getter and setter method
    public String getAlarm1()
    {
        return Alarm1;
    }
    public void setAlarm1(String Alarm1)
    {
        this.Alarm1 = Alarm1;
    }

}
