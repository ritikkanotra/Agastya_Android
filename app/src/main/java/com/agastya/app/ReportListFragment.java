package com.agastya.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agastya.app.model.Report;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportListFragment extends Fragment {

    private RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;
    private ReportsAdapter reportsAdapter;
    private RecyclerView recyclerView;
    private ProgressBar reportProgressBar;
    private TextView noReportsTextView;


    public ReportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report_list, container, false);

        requestQueue = Volley.newRequestQueue(getContext());
        sharedPreferences = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        recyclerView = view.findViewById(R.id.rv_reports);
        noReportsTextView = view.findViewById(R.id.tv_no_reports);

        reportProgressBar = view.findViewById(R.id.pb_reports);

        // TODO(fetch reports from DB)

        reportProgressBar.setVisibility(View.VISIBLE);
        noReportsTextView.setVisibility(View.GONE);

        List<Report> reportList = new ArrayList<>();

        String url = "http://10.0.2.2/temp/get_reports.php";
        StringRequest reportRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        reportList.add(new Report(
                                obj.getString("id"),
                                obj.getString("image_url"),
                                obj.getString("result"),
                                obj.getString("report_url"),
                                obj.getString("date_time")

                        ));
                    }
                    reportProgressBar.setVisibility(View.GONE);

                    if (reportList.isEmpty()) {
                        // no reports found
                        noReportsTextView.setVisibility(View.GONE);
                    }
                    else {
                        reportsAdapter = new ReportsAdapter(reportList);
                        recyclerView.setAdapter(reportsAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                reportProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Error getting reports", Toast.LENGTH_LONG).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", sharedPreferences.getString("id", "-1"));
                return params;
            }
        };

        requestQueue.add(reportRequest);

        // TODO(parse json and populate data)


        return view;
    }
}