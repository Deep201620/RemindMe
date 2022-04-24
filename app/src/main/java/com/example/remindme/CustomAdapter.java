package com.example.remindme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    ArrayList<HashMap<String,String>> alarmLabelAdapter;
    Context context;


    public CustomAdapter( Context context, ArrayList<HashMap<String,String>> alarmLabelAdapter) {
        this.alarmLabelAdapter = alarmLabelAdapter;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the item layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings, layout paramters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    //set the data in items
       holder.alarmHour.setText(String.valueOf(alarmLabelAdapter.get(position).get("AlarmHour")));
       holder.alarmMinute.setText(String.valueOf(alarmLabelAdapter.get(position).get("AlarmMinutes")));
       holder.alarmmeridiem.setText(String.valueOf(alarmLabelAdapter.get(position).get("AlarmMeridiem")));

        //implemnent onclciklistener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return alarmLabelAdapter.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView alarmHour;
        TextView alarmMinute;
        TextView alarmmeridiem;

        public MyViewHolder(View itemView){
            super(itemView);
            alarmHour = itemView.findViewById(R.id.alarm_hour);
            alarmMinute = itemView.findViewById(R.id.alarm_minute);
            alarmmeridiem = itemView.findViewById(R.id.alarm_meridiem);
        }
    }


}
