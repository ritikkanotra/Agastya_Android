package com.agastya.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.agastya.app.model.News;
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
import java.util.List;

public class NewsFragment extends Fragment {

    private RecyclerView newsRecyclerView;
    private RequestQueue requestQueue;
    private NewsAdapter newsAdapter;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        requestQueue = Volley.newRequestQueue(getContext());

        View view =  inflater.inflate(R.layout.fragment_news, container, false);

        newsRecyclerView = view.findViewById(R.id.rv_news);

        List<News> newsList = getNewsList();

        newsAdapter = new NewsAdapter(newsList);
        newsRecyclerView.setAdapter(newsAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private List<News> getNewsList() {
        String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";
        List<News> newsList = new ArrayList<>();

        StringRequest newsRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("rk_debug", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject newsObject = jsonArray.getJSONObject(i);
                        newsList.add(new News(
                                newsObject.getString("urlToImage"),
                                newsObject.getString("url"),
                                newsObject.getString("title")));
                    }
                    newsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error getting news", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(newsRequest);

        return newsList;

    }
}