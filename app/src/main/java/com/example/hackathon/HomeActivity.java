package com.example.hackathon;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hackathon.ui.account.AccountFragment;
import com.example.hackathon.ui.calender.CalenderFragment;
import com.example.hackathon.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity
        implements BottomNavigationView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnItemSelectedListener(this);

        // set home fragment by default (messaging page)
        setFragmentView(homeFragment);
    }

    HomeFragment homeFragment = new HomeFragment();
    CalenderFragment calenderFragment = new CalenderFragment();
    AccountFragment accountFragment = new AccountFragment();

    /**
     * Check what selected item is corresponding to and change the view for that item
      * @param item The selected item
     * @return boolean
     */
    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                setFragmentView(homeFragment);
                return true;

            case R.id.navigation_calender:
                setFragmentView(calenderFragment);
                return true;

            case R.id.navigation_account:
                setFragmentView(accountFragment);
                return true;
        }
        return false;
    }

    /**
     * Set the parameter to the main view
     * @param fragment
     */
    public void setFragmentView(Fragment fragment) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, fragment)
                    .commit();
        } catch (Exception e) {
            System.out.println("Setting fragment to view failed " + e);
        }
    }
}