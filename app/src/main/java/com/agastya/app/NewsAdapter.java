package com.agastya.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agastya.app.model.News;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView newsImageView;
        public TextView titleTextView;
        MaterialCardView newsView;

        public ViewHolder(View itemView) {

            super(itemView);
            newsImageView = itemView.findViewById(R.id.iv_news);
            titleTextView = itemView.findViewById(R.id.tv_title);
            newsView = itemView.findViewById(R.id.news_card_view);

        }

    }


    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View newsView = layoutInflater.inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(newsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        News news = newsList.get(position);

        TextView titleTextView = holder.titleTextView;
        titleTextView.setText(news.getTitle());
        ImageView imageView = holder.newsImageView;
        Glide.with(imageView.getContext()).load(news.getImageUrl()).centerCrop().into(imageView);
        MaterialCardView newsView = holder.newsView;
        newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newsUrlIntent = new Intent();
                newsUrlIntent.setAction(Intent.ACTION_VIEW);
                newsUrlIntent.setData(Uri.parse(news.getUrl()));
                newsView.getContext().startActivity(newsUrlIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
