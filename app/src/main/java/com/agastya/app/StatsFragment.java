package com.agastya.app;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatsFragment extends Fragment {

    private TextView countryCasesTextView;
    private TextView countryDeathsTextView;
    private TextView countryRecoveredTextView;
    private TextView countryActiveTextView;
    private TextView countryCriticalTextView;
    private TextView countryTodayCases;
    private TextView countryTodayDeaths;

    private RequestQueue requestQueue;

    private Location gpsLoc = null, networkLoc = null, finalLoc = null;

    private Double latitude = null, longitude = null;

    private String myCountry = null;

    public StatsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);

//        countryNameTextView = view.findViewById(R.id.country_name_text_view);
        countryCasesTextView = view.findViewById(R.id.country_cases_text_view);
        countryDeathsTextView = view.findViewById(R.id.country_death_text_view);
        countryRecoveredTextView = view.findViewById(R.id.country_recovered_text_view);
        countryActiveTextView = view.findViewById(R.id.country_active_text_view);
        countryCriticalTextView = view.findViewById(R.id.country_critical_text_view);
        countryTodayCases = view.findViewById(R.id.country_today_cases);
        countryTodayDeaths = view.findViewById(R.id.country_today_deaths);

        requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        loadCountryStats();


        return view;


    }

    public void loadCountryStats() {
        final String URL = "https://coronavirus-19-api.herokuapp.com/countries/india";

        StringRequest covidStatsRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    countryCasesTextView.setText(jsonObject.getString("cases"));
                    countryDeathsTextView.setText(jsonObject.getString("deaths"));
                    countryRecoveredTextView.setText(jsonObject.getString("recovered"));
                    countryActiveTextView.setText(jsonObject.getString("active"));
                    countryCriticalTextView.setText(jsonObject.getString("critical"));
                    countryTodayCases.setText(jsonObject.getString("todayCases"));
                    countryTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error loading data", Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(covidStatsRequest);
    }

}