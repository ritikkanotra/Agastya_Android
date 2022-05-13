package com.agastya.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.google.android.material.button.MaterialButton;

public class VaccineFragment extends Fragment {

    private MaterialButton btnVaccine;

    public VaccineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaccine, container, false);

        btnVaccine = view.findViewById(R.id.btn_register_vaccine);

        btnVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cowinIntent = new Intent();
                cowinIntent.setAction(Intent.ACTION_VIEW);
                cowinIntent.setData(Uri.parse("https://selfregistration.cowin.gov.in/"));
                view.getContext().startActivity(cowinIntent);
            }
        });



        return view;
    }
}