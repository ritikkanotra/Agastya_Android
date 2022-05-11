package com.agastya.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class VaccineFragment extends Fragment {

    private WebView cowinWebView;

    public VaccineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaccine, container, false);

        cowinWebView = view.findViewById(R.id.webview_cowin);

        cowinWebView.requestFocus();
        cowinWebView.getSettings().setLightTouchEnabled(true);
        cowinWebView.getSettings().setJavaScriptEnabled(true);
        cowinWebView.getSettings().setGeolocationEnabled(true);
        cowinWebView.setSoundEffectsEnabled(true);

//        cowinWebView.loadUrl("https://selfregistration.cowin.gov.in/");
        cowinWebView.loadUrl("https://www.youtube.com/watch?v = atnyX5yjLj0");
        cowinWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
//                    progressDialog.show();
                }
                if (progress == 100) {
//                    progressDialog.dismiss();
                }
            }
        });

        return view;
    }
}