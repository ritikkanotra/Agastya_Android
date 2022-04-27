package com.agastya.app;

public class CountryStats {

    private String mCountryName;
    private String mCases;
    private String mTodayCases;
    private String mDeaths;
    private String mTodayDeaths;
    private String mRecovered;
    private String mActive;
    private String mCritical;

    public CountryStats(String cases, String deaths, String recovered) {

        mCases = cases;
        mDeaths = deaths;
        mRecovered = recovered;

    }

    public CountryStats(String countryName,
                        String cases,
                        String todayCases,
                        String deaths,
                        String todayDeaths,
                        String recovered,
                        String active,
                        String critical) {

        mCountryName = countryName;
        mCases = cases;
        mTodayCases = todayCases;
        mDeaths = deaths;
        mTodayDeaths = todayDeaths;
        mRecovered = recovered;
        mActive = active;
        mCritical = critical;

    }

    public String getCountryName() {
        return mCountryName;
    }

    public String getCases() {
        return mCases;
    }

    public String getTodayCases() {
        return mTodayCases;
    }

    public String getDeaths() {
        return mDeaths;
    }

    public String getTodayDeaths() {
        return mTodayDeaths;
    }

    public String getRecovered() {
        return mRecovered;
    }

    public String getActive() {
        return mActive;
    }

    public String getCritical() {
        return mCritical;
    }
}
