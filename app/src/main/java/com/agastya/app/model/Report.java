package com.agastya.app.model;

public class Report {

    private String id;
    private String imageUrl;
    private String result;
    private String reportUrl;
    private String dateTime;

    public Report(String id, String imageUrl, String result, String  reportUrl, String dateTime) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.result = result;
        this.reportUrl = reportUrl;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public String getResult() {
        return result;
    }
}
