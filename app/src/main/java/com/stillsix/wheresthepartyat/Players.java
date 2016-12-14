package com.stillsix.wheresthepartyat;

/**
 * Created by sean.gallagher on 12/12/16.
 */
public class Players {
    public int bfgUID;
    public int icon;
    public String username;


    public Players(){
        super();
    }

    public Players(int bfgUID, int icon, String username) {
        super();
        this.bfgUID = bfgUID;
        this.icon = icon;
        this.username = username;
    }

    @Override
    public String toString() {
        return this.username ;
    }
}
