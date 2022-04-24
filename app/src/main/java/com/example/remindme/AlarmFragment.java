package com.example.remindme;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlarmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmFragment extends Fragment {

    //Added by Deep

//    ArrayList<Integer> alarmLabel = new ArrayList<>();

    FloatingActionButton fb1;
    View v;
    CustomAdapter adapter;
    private RecyclerView recyclerView;
    public static DBHelper db;
    SQLiteDatabase sqLiteDatabase;
    static ArrayList<HashMap<String,String>> alarmData = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AlarmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlarmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlarmFragment newInstance(String param1, String param2) {
        AlarmFragment fragment = new AlarmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

        //initializing DBHelper and SQLiteDtabase objects
        db = new DBHelper(getContext());
        sqLiteDatabase = db.getWritableDatabase();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Added by Deep
        v = inflater.inflate(R.layout.fragment_alarm, container, false);


        fb1 = v.findViewById(R.id.addalarm);

            fb1.setOnClickListener(view -> {

            Intent intent = new Intent(getContext(), Create_alarm.class);
            startActivity(intent);
        });

        //getting the reference of recyclerview
        RecyclerView recyclerView =v.findViewById(R.id.alarmlist);


        //setting a layout with orientation vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager); //setting layoutmanager to recyclerview


        // call the constructor of CustomAdapter to send the reference and data to Adapter
        alarmData = db.fetchdata();
        System.out.println(alarmData);
        adapter = new CustomAdapter(getContext(), alarmData);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment

        return v;

    }
}