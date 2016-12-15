package com.stillsix.wheresthepartyat;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CityLeadersActivity extends AppCompatActivity {

    private ListView listView1;
    //DBAdapter customAdapter;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_leaders);

        //Create an array of the data
        CityLeadersDataArrayHC cityleaders_data[] = new CityLeadersDataArrayHC[]
                {
                        new CityLeadersDataArrayHC(1, R.drawable.seattle, "Seattle", 5200),
                        new CityLeadersDataArrayHC(2, R.drawable.sanfran, "San Francisco", 4210),
                        new CityLeadersDataArrayHC(3, R.drawable.newyorkcity, "New York City", 3923),
                        new CityLeadersDataArrayHC(4, R.drawable.tokyo, "Tokyo", 2131),

                };


        CityLeadersAdapter adapter = new CityLeadersAdapter(this,
                R.layout.cityleaders_item_row, cityleaders_data);

        listView1 = (ListView) findViewById(R.id.cityleaders_list);


        View header = (View) getLayoutInflater().inflate(R.layout.cityleaderslist_header_row, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Object listitem = listView1.getItemIdAtPosition(position);

                TextView v = (TextView) view.findViewById(R.id.cityNameTxt);
                TextView counts = (TextView) view.findViewById(R.id.cityPlayersTxt);
                /*ImageView diet = (ImageView) view.findViewById(R.id.dietIcon);
                ImageView era = (ImageView) view.findViewById(R.id.eraIcon);
                ImageView element = (ImageView) view.findViewById(R.id.elementIcon);*/

                Toast.makeText(getApplicationContext(), "Selected:" + v.getText(), Toast.LENGTH_LONG).show();
                //String item = ((TextView) view).getText().toString();

                Log.d("onItemClick:", "CLICKED:" + listitem);

                String testServerURL = "http://172.16.53.240:8881/city";

                OkHttpClient client = new OkHttpClient();
                HttpUrl.Builder urlBuilder = HttpUrl.parse(testServerURL).newBuilder();
                urlBuilder.addQueryParameter("appName", "Fairway");
                urlBuilder.addQueryParameter("country", "US");
                //urlBuilder.addQueryParameter("city", "Seattle");
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
                        Log.v("City Leaders Activity", "Entered response");
                        try {
                            Log.v("City Leaders Activity", "Entered Try Catch");
                            String responseData = response.body().string();
                            if (response.isSuccessful()) {
                                //Log.v(TAG, response.body().string());
                                parseLocationData(responseData);
                                //LocationData[] mLocationData = parseLocationData(responseData);
                                //Log.v("Main Activity", "WHat's in the Carnivores Array:"+ Arrays.toString(listOfCarnivores));
                            } else {
                                Log.v("City Leaders Activity", "You got an error on response for json");
                            }
                            //JSONObject json = new JSONObject(responseData);
                            //JSONArray json = new JSONArray(responseData);
                            //JSONArray json = responseData.getInt("total");
                            //final String total = json.getString("total");
                            //Log.d("Map Activity", "Called Narayan:"+total);
                        } catch (IOException e) {
                            Log.e("City Leaders Activity", "IO Exception caught:", e);
                        } catch (JSONException e) {
                            Log.e("City Leaders Activity", "Json exception caught:", e);
                        }
                    }
                });

            }
        });
    }
    private String parseLocationData(String jsonData) throws JSONException {
        String locationData = jsonData;
        Log.d("City Leaders Activity", "Parsing Json" + locationData);
        //LocationData locationData = new LocationData();
        //locationData.setDisplayLocationData(getDisplayLocationData(jsonData));
        return locationData;
    }


    private CityLeadersData[] getDisplayLocationData(String jsonData) throws JSONException {

        JSONObject locationData = new JSONObject(jsonData);
        JSONArray locationDisplay = locationData.getJSONArray("cities");
        CityLeadersData[] displayDetails = new CityLeadersData[locationDisplay.length()];
        for (int i=0; i < locationDisplay.length(); i++) {
            //Log.v(TAG,"for loop entered");
            JSONObject jsonDisplay = locationDisplay.getJSONObject(i);
            Log.v("City Leaders Activity", i + " got some data within the json array" + jsonDisplay);
            CityLeadersData cityResults = new CityLeadersData();
            //cityResults.getCountryTotal(jsonDisplay.getInt("total"));
            //cityResults.setCityName(jsonDisplay.getString("city"));
            cityResults.setCities(getCitiesData(jsonDisplay, jsonDisplay.getString("name"), i));
            displayDetails[i] = cityResults;
        }
        //Log.d("Map Activity", "Got data:"+Arrays.toString(displayDetails));
        return displayDetails;
    }

    private Cities[] getCitiesData(JSONObject jsonData, String dinoName, int dinoArrayID) throws JSONException {
        JSONArray citiesArray = jsonData.getJSONArray("cities");
        Log.v("City Leaders Activity", "Show cities array:" + citiesArray);
        //Create an array for the attributes and set its length to the length of the attribute data
        Cities[] citiesDetails = new Cities[citiesArray.length()];
        for (int i=0; i < citiesArray.length(); i++) {
            //Create an object at each position of the attribute JSON data
            JSONObject jsonAttributes = citiesArray.getJSONObject(i);
            //Create a variable to populate the new model object for attributes
            Cities attributeData = new Cities();
            //Use the Attributes model and set the values from the JSON
            attributeData.setCityName(jsonAttributes.getString("city"));
            attributeData.setTotal(jsonAttributes.getInt("total"));
            citiesDetails[i] = attributeData;
        }
        return citiesDetails;
    }

    public void back2Map(View view) {
        Intent mapIntent = new Intent(this, MapActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putCharSequence("unlock", "true");
        //bundle.putBoolean("unlock", true);
        //mapIntent.putExtras(bundle);
        startActivity(mapIntent);

    }
}
