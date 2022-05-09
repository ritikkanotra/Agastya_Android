package com.agastya.app;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class ReportFragment extends Fragment {

    public ReportFragment() {
        // Required empty public constructor
    }

//    FrameLayout reportFrameLayout;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();



        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sp",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String id = sharedPreferences.getString("id", "-1");

        if (id.equals("-1")) {
            fragmentManager.beginTransaction().add(R.id.report_frame_layout, new LoginFragment()).commit();
        }
        else {
            fragmentManager.beginTransaction().add(R.id.report_frame_layout, new ReportListFragment()).commit();
        }




        return view;
    }
}