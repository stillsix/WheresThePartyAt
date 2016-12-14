package com.stillsix.wheresthepartyat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import java.util.Arrays;

public class MapActivity extends AppCompatActivity {

    //public String unlockStatus = "false";
    public Boolean unlockStatus = false;
    public ImageView display_unlock;
    public TextView display_seattle, display_la, display_sanfran;
    private LocationData mLocationData;

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
        //String jsonURL = "https://raw.githubusercontent.com/stillsix/DinoDictionaryV1/master/app/src/main/res/raw/dinosaurs.json";
        String testServerURL = "http://172.16.53.240:8881/testCity";

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(testServerURL).newBuilder();
        urlBuilder.addQueryParameter("appName", "Fairway");
        urlBuilder.addQueryParameter("city", "seattle");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    final String result = json.getString("result");
                    Log.d("Map Activity", "Called Narayan:"+result);
                } catch (IOException e) {
                    Log.e("Map Activity", "IO Exception caught:", e);
                } catch (JSONException e) {
                    Log.e("Map Activity", "Json exception caught:", e);
                }
            }
            /*@Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.d("Got Data", jsonData);
                    if (response.isSuccessful()) {
                        //Log.v(TAG, response.body().string());
                        mLocationData = parseDinoDetails(jsonData);
                        //Log.d("Map Activity", "WHat's in the Carnivores Array:" + Arrays.toString(listOfCarnivores));
                    } else {
                        Log.d("Map Activity", "You got an error");
                    }
                } catch (IOException e) {
                    Log.e("Map Activity", "IO Exception caught:", e);
                } catch (JSONException e) {
                    Log.e("Map Activity", "Json exception caught:", e);
                }
            }*/
        });
    }

    private LocationData parseDinoDetails(String jsonData) throws JSONException {
        LocationData locationData = new LocationData();

        locationData.setDisplayLocationData(getDisplayLocationData(jsonData));
        return locationData;
    }
    private DisplayLocationData[] getDisplayLocationData(String jsonData) throws JSONException {

        JSONObject locationData = new JSONObject(jsonData);

        JSONArray locationDisplay = locationData.getJSONArray("dinosaurs");
        DisplayLocationData[] displayDetails = new DisplayLocationData[locationDisplay.length()];
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
            //Log.v(TAG, "Finished special call to attributes");
            displayDetails[i] = locationDisplayDetails;
        }
        Log.d("Map Activity", "Got data:"+Arrays.toString(displayDetails));
        return displayDetails;
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
