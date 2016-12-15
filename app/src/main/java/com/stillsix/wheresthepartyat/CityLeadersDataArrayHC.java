package com.stillsix.wheresthepartyat;

/**
 * Created by sean.gallagher on 12/14/16.
 */
public class CityLeadersDataArrayHC {
    public int bfgUID;
    public int icon;
    public String cityName;
    public int playerCount;


    public CityLeadersDataArrayHC(){
        super();
    }

    public CityLeadersDataArrayHC(int bfgUID, int icon, String cityName, int playerCount) {
        super();
        this.bfgUID = bfgUID;
        this.icon = icon;
        this.cityName = cityName;
        this.playerCount = playerCount;
    }

    @Override
    public String toString() {
        return this.cityName ;
    }
}