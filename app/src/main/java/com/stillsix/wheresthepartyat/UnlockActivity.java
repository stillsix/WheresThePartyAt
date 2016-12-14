package com.stillsix.wheresthepartyat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UnlockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
    }
    public void unlockClick(View view) {
        Intent mapIntent = new Intent(this, MapActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putCharSequence("unlock", "true");
        bundle.putBoolean("unlock", true);
        mapIntent.putExtras(bundle);
        startActivity(mapIntent);

        /*display_unlock = (ImageView) findViewById(R.id.lockButton);
        int res = getResources().getIdentifier("unlockicon", "drawable", this.getPackageName());
        display_unlock.setImageResource(res);*/

    }
    public void inviteFriends(View view) {
        Intent inviteIntent = new Intent(this, InviteActivity.class);
        //Bundle bundle = new Bundle();
        //bundle.putCharSequence("unlock", "true");
        //bundle.putBoolean("unlock", true);
        //inviteIntent.putExtras(bundle);
        startActivity(inviteIntent);

    }
}
