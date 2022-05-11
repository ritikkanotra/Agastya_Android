package com.agastya.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int currentItem;
    private FragmentManager fragmentManager;
    private TextView headingTextView;
    private final static int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private ImageView logoutBtn;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        currentItem = R.id.nav_item_stats;
        fragmentManager = getSupportFragmentManager();
        headingTextView = findViewById(R.id.tv_heading);
        logoutBtn = findViewById(R.id.btn_logout);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        sp = getSharedPreferences("sp", MODE_PRIVATE);
        spEditor = sp.edit();

        initNavBar();


        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            //TODO
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_STORAGE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }

    public void onClickNavItem(View view) {
        int item = view.getId();
        if (currentItem != item) {
            String heading = "";
            switch (item) {

                case R.id.nav_item_stats:
                    logoutBtn.setVisibility(View.GONE);
                    heading = "Statistics";
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new StatsFragment()).commit();
                    break;

                case R.id.nav_item_news:
                    logoutBtn.setVisibility(View.GONE);
                    heading = "Covid News";
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new NewsFragment()).commit();
                    break;

                case R.id.nav_item_res:
                    heading = "Helpline";
                    logoutBtn.setVisibility(View.GONE);
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new PhoneFragment()).commit();
                    break;

                case R.id.nav_item_vaccine:
                    heading = "Vaccine";
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new VaccineFragment()).commit();
                    logoutBtn.setVisibility(View.GONE);
                    break;

                case R.id.nav_item_report:
                    initLogoutBtn();
                    heading = "Report";
                    fragmentManager.beginTransaction().replace(R.id.fragment_frame, new ReportFragment()).commit();
                    break;
            }

            currentItem = item;
            headingTextView.setText(heading);

        }

    }

    public void initNavBar() {
        logoutBtn.setVisibility(View.GONE);
        fragmentManager.beginTransaction().add(R.id.fragment_frame, new StatsFragment()).commit();
    }

    private void logout() {
        fragmentManager.beginTransaction().replace(R.id.report_frame_layout, new LoginFragment()).commit();
        spEditor.putString("id", "-1").commit();
        Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
        initLogoutBtn();
    }

    private void initLogoutBtn() {
        if (sp.getString("id", "-1").equals("-1")) {
            logoutBtn.setVisibility(View.GONE);
        }
        else {
            logoutBtn.setVisibility(View.VISIBLE);
        }
    }
}