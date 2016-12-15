package com.stillsix.wheresthepartyat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MapActivity extends AppCompatActivity {

    //public String unlockStatus = "false";
    public Boolean unlockStatus = false;
    public ImageView display_unlock;
    public TextView display_yourlocation, display_newyork, display_la, display_sanfran;
    //private LocationData mLocationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //Determine display status of lock icon
        Intent incomingIntent = getIntent();
        Bundle bundle2 = incomingIntent.getExtras();
        if (bundle2 != null) {
            //unlockStatus = bundle2.getString("unlock");
            unlockStatus = bundle2.getBoolean("unlock");
            Log.d("UNLOCK STATUS:", unlockStatus+"");
            if (unlockStatus)
            {
                Log.d("Call to UNLOCK IMAGE:", unlockStatus+"");
                display_unlock = (ImageView) findViewById(R.id.lockButton);
                int res = getResources().getIdentifier("unlockicon", "drawable", this.getPackageName());
                display_unlock.setImageResource(res);
            } else {
                Log.d("Call to UNLOCK Failed:", unlockStatus+"");
            }
        }


    //Get Data from Source
        String testServerURL = "http://172.16.53.240:8888/city";

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(testServerURL).newBuilder();
        urlBuilder.addQueryParameter("appName", "Fairway");
        urlBuilder.addQueryParameter("city", "Seattle");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("Map Activity", "Request for JSON epic failed");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("Map Activity", "Entered response");
                try {
                    Log.v("Map Activity", "Entered Try Catch");
                    if (response.isSuccessful()) {
                        //parseLocationData(responseData);
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        parseLocationData(jsonObject);
                        //LocationData[] mLocationData = parseLocationData(responseData);
                        //Log.v("Main Activity", "WHat's in the Carnivores Array:"+ Arrays.toString(listOfCarnivores));
                    } else {
                        Log.v("Main Activity", "You got an error on response for json");
                    }
                /*} catch (IOException e) {
                    Log.v("Map Activity", "IO Exception caught:", e);*/
                } catch (JSONException e) {
                    Log.v("Map Activity", "Json exception caught:", e);
                }
            }
        });
    }

    private int parseLocationData(JSONObject jsonObject) throws JSONException {
        //String locationData = jsonData.getInt("total");
        //DisplayLocationData[] locationTotal = new DisplayLocationData[jsonData.length()];
        int total  = jsonObject.getInt("total");
        JSONArray raveIds = jsonObject.getJSONArray("raveIds");
        JSONObject userInf = jsonObject.getJSONObject("userInfo");
        display_yourlocation = (TextView) findViewById(R.id.yourLocationTxt);
        display_yourlocation.setText("Your location:" +total);
        return total;
    }
    /*private LocationData parseLocationData(String jsonData) throws JSONException {
        LocationData locationData = new LocationData();

        locationData.setDisplayLocationData(getDisplayLocationData(jsonData));
        return locationData;
    }*/

    private int getDisplayLocationData(String jsonData) throws JSONException {

        //private DisplayLocationData[] getDisplayLocationData(String jsonData) throws JSONException {

        JSONObject locationData = new JSONObject(jsonData);
        int locationResults = locationData.getInt("total");
        //JSONArray locationDisplay = locationData.getJSONArray("dinosaurs");
        /*DisplayLocationData[] displayDetails = new DisplayLocationData[locationDisplay.length()];
        for (int i=0; i < locationDisplay.length(); i++) {
            //Log.v(TAG,"for loop entered");
            JSONObject jsonDisplay = locationDisplay.getJSONObject(i);
            Log.v("MapActivity", i + " version created" + jsonDisplay);
            DisplayLocationData locationDisplayDetails = new DisplayLocationData();

            locationDisplayDetails.setName(jsonDisplay.getString("name"));
            locationDisplayDetails.setPronunication(jsonDisplay.getString("pronunciation"));
            locationDisplayDetails.setMainImageURL(jsonDisplay.getString("mainImageURL"));
            locationDisplayDetails.setThumbnailImageURL(jsonDisplay.getString("thumbnailImageURL"));
            //dinoDisplayDetails.setAttributes(getAttributeData(jsonDisplay, jsonDisplay.getString("name"), i));
            displayDetails[i] = locationDisplayDetails;
        }*/
        //Log.d("Map Activity", "Got data:"+Arrays.toString(displayDetails));
        //return displayDetails;
        //String results = locationResults+"";
        return locationResults;
    }

    public void cityLeaders(View view) {
        Intent leadersIntent = new Intent(this, CityLeadersActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putCharSequence("unlock", "true");
        //bundle.putBoolean("unlock", true);
        //mapIntent.putExtras(bundle);
        startActivity(leadersIntent);

    }

    public void onClick(View view) {
        Intent unlockIntent = new Intent(this, UnlockActivity.class);
        startActivity(unlockIntent);

        /*display_unlock = (ImageView) findViewById(R.id.lockButton);
        int res = getResources().getIdentifier("unlockicon", "drawable", this.getPackageName());
        display_unlock.setImageResource(res);*/

    }


    public void getLocationStatus(View view) {
        Toast.makeText(this, "Number of players still needed:", Toast.LENGTH_SHORT).show();
    }
}
