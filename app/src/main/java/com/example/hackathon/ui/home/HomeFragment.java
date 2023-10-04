package com.example.hackathon.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.hackathon.CreateActivity;
import com.example.hackathon.R;

public class HomeFragment extends Fragment {

    public HomeFragment(){
        // require a empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button b = (Button) view.findViewById(R.id.createButton);
        b.setOnClickListener(new View.OnClickListener() {

        @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), CreateActivity.class);
                startActivity(in);
            }
        });

        return view;
    }
}