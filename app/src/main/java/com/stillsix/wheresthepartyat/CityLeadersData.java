package com.stillsix.wheresthepartyat;

/**
 * Created by sean.gallagher on 12/15/16.
 */
public class CityLeadersData {
    private int mCountryTotal;
    private Cities[] mCities;

    public int getCountryTotal() {
        return mCountryTotal;
    }

    public void setCountryTotal(int countryTotal) {
        mCountryTotal = countryTotal;
    }
    public Cities[] getCities() {
        return mCities;
    }

    public void setCities(Cities[] cities) {
        mCities = cities;
    }
}
