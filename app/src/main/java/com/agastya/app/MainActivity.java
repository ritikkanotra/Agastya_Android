package com.agastya.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int currentItem;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        currentItem = R.id.nav_item_stats;
        fragmentManager = getSupportFragmentManager();

        initNavBar();

    }

    public void onClickNavItem(View view) {
        int item = view.getId();
        if (currentItem != item) {
            String heading;
            switch (item) {

                case R.id.nav_item_stats:
                    heading = "Statistics";
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new StatsFragment()).commit();
                    break;

                case R.id.nav_item_info:
                    break;

                case R.id.nav_item_res:
                    break;

                case R.id.nav_item_vaccine:
                    break;
            }

        }

    }

    public void initNavBar() {
        fragmentManager.beginTransaction().add(R.id.fragment_frame, new StatsFragment()).commit();
    }
}