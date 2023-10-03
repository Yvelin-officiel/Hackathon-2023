package com.example.hackathon;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackathon.ui.account.AccountFragment;
import com.example.hackathon.ui.calender.CalenderFragment;
import com.example.hackathon.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity
        implements BottomNavigationView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnItemSelectedListener(this);
    }
    HomeFragment homeFragment = new HomeFragment();
    CalenderFragment calenderFragment = new CalenderFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, homeFragment)
                        .commit();
                return true;

            case R.id.navigation_calender:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, calenderFragment)
                        .commit();
                return true;

            case R.id.navigation_account:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, accountFragment)
                        .commit();
                return true;
        }
        return false;
    }
}