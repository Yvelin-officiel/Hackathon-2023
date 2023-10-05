package com.example.hackathon.ui.calender;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.hackathon.CreateActivity;
import com.example.hackathon.R;

import java.util.Date;

public class CalenderFragment extends Fragment {

    public CalenderFragment(){
        // require a empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender, container, false);

        // Button to create an event
        Button b = (Button) view.findViewById(R.id.eventCreate);
        b.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), CreateActivity.class);
            startActivity(in);
        });

        // Get selected day in the calendar
        DatePicker datePicker = view.findViewById(R.id.datePicker);
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth)
            {
                Toast.makeText(getContext(), ""+dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}