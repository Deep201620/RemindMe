package com.example.remindme;

import static android.content.ContentValues.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.remindme.databinding.ActivityCreateAlarmBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class Create_alarm extends AppCompatActivity {

    private ActivityCreateAlarmBinding binding;
    private MaterialTimePicker picker;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar calender;
    DBHelper db;
    SQLiteDatabase sqldb;
    String hour,minute, meridiem;
    //Added by Deep


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();



        //setting onClickListener
        binding.selectedTime.setOnClickListener(view -> {

            //Time Picker
            showTimePicker();

        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.setAlarm.setOnClickListener(view -> setAlarm());
        }


        binding.cancelAlarm.setOnClickListener(view -> {

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setAlarm() {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        /* use InexactRepeating() method for better app performance
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calender.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent)  ;
        */

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                calender.getTimeInMillis(),
                pendingIntent);


        db = new DBHelper(Create_alarm.this);
        sqldb = db.getWritableDatabase();
        hour = binding.time.getText().toString().substring(0,2);
        minute = binding.time.getText().toString().substring(3,5);
        meridiem = binding.time.getText().toString().substring(5);

        ContentValues contentValues = new ContentValues();
        contentValues.put(AlarmSchema.Alarm_Colns.Col_Alarm_Hour, hour);
        contentValues.put(AlarmSchema.Alarm_Colns.Col_Alarm_Minute,minute);
        contentValues.put(AlarmSchema.Alarm_Colns.Col_meridiem,meridiem);
        long id = sqldb.insert(AlarmSchema.Alarm_Colns.TABLE_NAME, null, contentValues);

        if (id == 0) {
            System.out.println("Failure, Data not Inserted");
        } else {
            System.out.println("Success, Data Inserted Successfully");
        }


        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(this, AlarmFragment.class);
        startActivity(intent2);
    }

    /*
     *  Method providing user functionality to select the time
     * */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showTimePicker() {
        /*
         * Picker Object
         * Properties to set default time while opening the time picker
         * */
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        /*
         * Show the picker using show method
         * */
        picker.show(getSupportFragmentManager(), "bitsandbytes");


        /*
         * Using Positive Button Click Listener
         * to set the user selected time in the text view
         * */
        picker.addOnPositiveButtonClickListener(this::onClick);
    }

    //creating notification channel if app is running on oreo and above
    private  void  createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "alarmReminderChannel";
            String description = "Channel for alarm manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("bitsandbites",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onClick(View view) {
        if (picker.getHour() > 12) {
            binding.time.setText(
                    String.format("%02d", (picker.getHour() - 12)) + ":" + String.format("%02d", picker.getMinute()) + " PM");

        } else {
            binding.time.setText(String.format("%02d", (picker.getHour())) + ":" + picker.getMinute() + " AM");
        }

        calender = Calendar.getInstance();
        calender.set(Calendar.HOUR_OF_DAY, picker.getHour());
        calender.set(Calendar.MINUTE, picker.getMinute());
        calender.set(Calendar.SECOND, 0);
        calender.set(Calendar.MILLISECOND, 0);


    }



}