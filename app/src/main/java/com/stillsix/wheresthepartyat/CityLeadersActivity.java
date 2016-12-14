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

public class CityLeadersActivity extends AppCompatActivity {

    private ListView listView1;
    //DBAdapter customAdapter;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_leaders);

        //Create an array of the data
        CityLeadersData cityleaders_data[] = new CityLeadersData[]
                {
                        new CityLeadersData(1, R.drawable.seattle, "Seattle", 5200),
                        new CityLeadersData(2, R.drawable.sanfran, "San Francisco", 4210),
                        new CityLeadersData(3, R.drawable.newyorkcity, "New York City", 3923),
                        new CityLeadersData(4, R.drawable.tokyo, "Tokyo", 2131),

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

                /*Intent unlockIntent = new Intent(CityLeadersActivity.this, UnlockActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("position", position);
                bundle.putCharSequence("image", v.getText());
                bundle.putCharSequence("title", v.getText());
                *//*bundle.putDouble();"diet", diet.getDrawable());
                bundle.putCharSequence("element", element.getText());
                bundle.putCharSequence("era", era.getText());*//*
                unlockIntent.putExtras(bundle);
                startActivity(unlockIntent);*/

                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

            }
        });
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
