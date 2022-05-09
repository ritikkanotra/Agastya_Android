package com.agastya.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {


    private TextInputEditText unameEditText, pwdEditText;
    private RequestQueue requestQueue;
    private MaterialButton loginButton;
    private FragmentManager fragmentManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        fragmentManager = getActivity().getSupportFragmentManager();

        unameEditText = view.findViewById(R.id.et_uname);
        pwdEditText = view.findViewById(R.id.et_pwd);
        loginButton = view.findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeLoginRequest(unameEditText.getText().toString(), pwdEditText.getText().toString());
            }
        });

        sharedPreferences = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();



        return view;
    }

    public void makeLoginRequest(String uname, String pwd) {
        loginButton.setEnabled(false);

        String loginUrl = "http://10.0.2.2/temp/login.php";

        StringRequest loginRequest = new StringRequest(Request.Method.POST, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("rk_debug", "Request Successful: " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("0")) {
                        loginButton.setEnabled(true);
                        Toast.makeText(getContext(), "Patient not found", Toast.LENGTH_SHORT).show();
                    }
                    else if (status.equals("-1")) {
                        loginButton.setEnabled(true);
                        Toast.makeText(getContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                    else if (status.equals("1")) {
                        Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        editor.putString("id", jsonObject.getString("id")).commit();
                        fragmentManager.beginTransaction().replace(R.id.report_frame_layout, new ReportListFragment()).commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loginButton.setEnabled(true);
                Log.d("rk_debug", "Request Failed: " + error);
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("uname", uname);
                params.put("pwd", pwd);
                return params;
            }
        };

        requestQueue.add(loginRequest);
    }
}