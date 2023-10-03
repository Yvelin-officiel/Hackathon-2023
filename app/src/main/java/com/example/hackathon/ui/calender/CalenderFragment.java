package com.example.hackathon.ui.calender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.hackathon.R;

public class CalenderFragment extends Fragment {

    public CalenderFragment(){
        // require a empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calender, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}