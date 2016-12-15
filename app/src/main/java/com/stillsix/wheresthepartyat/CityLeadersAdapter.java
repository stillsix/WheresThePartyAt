package com.stillsix.wheresthepartyat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CityLeadersAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    CityLeadersDataArrayHC data[] = null;

    public CityLeadersAdapter(Context context, int layoutResourceId, CityLeadersDataArrayHC[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PlayerHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PlayerHolder();
            holder.cityIcon = (ImageView) row.findViewById(R.id.cityIcon);
            holder.txtCityName = (TextView) row.findViewById(R.id.cityNameTxt);
            holder.txtPlayerCount = (TextView) row.findViewById(R.id.cityPlayersTxt);

            row.setTag(holder);
        } else {
            holder = (PlayerHolder) row.getTag();
        }

        CityLeadersDataArrayHC cityleaders = data[position];
        holder.cityIcon.setImageResource(cityleaders.icon);
        holder.txtCityName.setText(cityleaders.cityName);
        holder.txtPlayerCount.setText(cityleaders.playerCount+"");

        return row;
    }

    static class PlayerHolder {
        ImageView cityIcon;
        TextView txtCityName;
        TextView txtPlayerCount;
    }
}
