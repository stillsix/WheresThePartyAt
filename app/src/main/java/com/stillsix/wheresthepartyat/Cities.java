package com.stillsix.wheresthepartyat;

/**
 * Created by sean.gallagher on 12/15/16.
 */
public class Cities {
    private String mCityName;
    private int mTotal;
    private PlayersRaveId mPlayerIds;
    private PlayersRaveInfo mPlayInfo;

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        this.mCityName = cityName;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int mTotal) {
        this.mTotal = mTotal;
    }

    public PlayersRaveId getPlayerIds() {
        return mPlayerIds;
    }

    public void setPlayerIds(PlayersRaveId mPlayerIds) {
        this.mPlayerIds = mPlayerIds;
    }

    public PlayersRaveInfo getPlayInfo() {
        return mPlayInfo;
    }

    public void setPlayInfo(PlayersRaveInfo mPlayInfo) {
        this.mPlayInfo = mPlayInfo;
    }


}
