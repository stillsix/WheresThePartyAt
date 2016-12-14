package com.stillsix.wheresthepartyat;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    Players data[] = null;

    public PlayerAdapter(Context context, int layoutResourceId, Players[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PlayerHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PlayerHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtUsername = (TextView)row.findViewById(R.id.txtUsername);

            row.setTag(holder);
        }
        else
        {
            holder = (PlayerHolder)row.getTag();
        }

        Players player = data[position];
        holder.txtUsername.setText(player.username);
        holder.imgIcon.setImageResource(player.icon);

        return row;
    }

    static class PlayerHolder
    {
        ImageView imgIcon;
        TextView txtUsername;
    }
}
