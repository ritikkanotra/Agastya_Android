package com.agastya.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int currentItem;
    private FragmentManager fragmentManager;
    private TextView headingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        currentItem = R.id.nav_item_stats;
        fragmentManager = getSupportFragmentManager();
        headingTextView = findViewById(R.id.tv_heading);

        initNavBar();

    }

    public void onClickNavItem(View view) {
        int item = view.getId();
        if (currentItem != item) {
            String heading = "";
            switch (item) {

                case R.id.nav_item_stats:
                    heading = "Statistics";
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new StatsFragment()).commit();
                    break;

                case R.id.nav_item_news:
                    heading = "Covid News";
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new NewsFragment()).commit();
                    break;

                case R.id.nav_item_res:
                    break;

                case R.id.nav_item_vaccine:
                    break;

                case R.id.nav_item_report:
                    heading = "Report";
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new ReportFragment()).commit();
                    break;
            }

            currentItem = item;
            headingTextView.setText(heading);

        }

    }

    public void initNavBar() {
        fragmentManager.beginTransaction().add(R.id.fragment_frame, new StatsFragment()).commit();
    }
}