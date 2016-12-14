package com.stillsix.wheresthepartyat;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




public class InviteActivity extends Activity {

    private ListView listView1;
    //DBAdapter customAdapter;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        //Create an array of the data
        Players player_data[] = new Players[]
                {
                        new Players(1, R.drawable.paul, "Picman"),
                        new Players(2, R.drawable.briang, "Goldenboy"),
                        new Players(3, R.drawable.corey, "Cowboy"),
                        new Players(4, R.drawable.narayan, "DrinkIt"),
                        new Players(5, R.drawable.carey, "Oaktown"),
                        new Players(6, R.drawable.seanc, "daBoss"),
                        new Players(7, R.drawable.andy, "IHeartEcomm"),
                        new Players(8, R.drawable.matt, "RememberMe"),
                        new Players(9, R.drawable.eli, "GotKidz"),
                        new Players(10, R.drawable.gina, "AddysMama"),

                };


        PlayerAdapter adapter = new PlayerAdapter(this,
                R.layout.listview_item_row, player_data);

        listView1 = (ListView) findViewById(R.id.localPlayers_list);


        View header = (View) getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Object listitem = listView1.getItemIdAtPosition(position);

                TextView v = (TextView) view.findViewById(R.id.txtUsername);
                /*ImageView diet = (ImageView) view.findViewById(R.id.dietIcon);
                ImageView era = (ImageView) view.findViewById(R.id.eraIcon);
                ImageView element = (ImageView) view.findViewById(R.id.elementIcon);*/

                Toast.makeText(getApplicationContext(), "Selected:" + v.getText(), Toast.LENGTH_LONG).show();
                //String item = ((TextView) view).getText().toString();

                Log.d("onItemClick:", "CLICKED:" + listitem);

                Intent unlockIntent = new Intent(InviteActivity.this, UnlockActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("position", position);
                bundle.putCharSequence("image", v.getText());
                bundle.putCharSequence("title", v.getText());
                /*bundle.putDouble();"diet", diet.getDrawable());
                bundle.putCharSequence("element", element.getText());
                bundle.putCharSequence("era", era.getText());*/
                unlockIntent.putExtras(bundle);
                startActivity(unlockIntent);

                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

            }
        });
    }
    public void back2Map(View view) {
        Intent mapIntent = new Intent(this, MapActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putCharSequence("unlock", "true");
        bundle.putBoolean("unlock", true);
        mapIntent.putExtras(bundle);
        Toast.makeText(this, "You've invited them to play", Toast.LENGTH_SHORT).show();
        startActivity(mapIntent);

    }
}



