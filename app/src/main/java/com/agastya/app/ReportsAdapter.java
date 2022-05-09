package com.agastya.app;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agastya.app.model.News;
import com.agastya.app.model.Report;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ViewHolder> {

    private List<Report> reportList;
    private DownloadManager downloadManager;
    private Context context;

    public ReportsAdapter(List<Report> reportList) {
        this.reportList = reportList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView reportIdTextView;
        public TextView dateTimeTextView;
        private RelativeLayout downloadLayout;
        private FrameLayout statusStrip;

        public ViewHolder(View itemView) {

            super(itemView);
            reportIdTextView = itemView.findViewById(R.id.tv_report_id);
            dateTimeTextView = itemView.findViewById(R.id.tv_date_time);
            downloadLayout = itemView.findViewById(R.id.layout_download_report);
            statusStrip = itemView.findViewById(R.id.layout_strip);

            context = itemView.getContext();

            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        }

    }


    @NonNull
    @Override
    public ReportsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View reportView = layoutInflater.inflate(R.layout.item_report, parent, false);
        ReportsAdapter.ViewHolder viewHolder = new ReportsAdapter.ViewHolder(reportView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsAdapter.ViewHolder holder, int position) {

        Report report = reportList.get(position);

        TextView reportIdTextView = holder.reportIdTextView;
        reportIdTextView.setText("Report ID: " +  report.getId());

        TextView dateTimeTextView = holder.dateTimeTextView;
        dateTimeTextView.setText("Date/Time: " + report.getDateTime());

        holder.downloadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(report.getReportUrl().equals("")) {
                    Toast.makeText(view.getContext(), "Report not found!", Toast.LENGTH_SHORT).show();
                }
                else {
                    downloadFile(report.getReportUrl(), report.getId());
                }
            }
        });

        if (report.getResult().equals("1")) {
            holder.statusStrip.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }
        else if (report.getResult().equals("0")) {
            holder.statusStrip.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
        }
        else {
            holder.statusStrip.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        }


    }


    private void downloadFile(String url, String id) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Downloading your report");
        request.setTitle("Report " + id);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Agastya/" + id + ".pdf");
        Toast.makeText(context, "Downloading report", Toast.LENGTH_SHORT).show();
        downloadManager.enqueue(request);

    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }


}
