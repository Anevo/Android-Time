package com.example.cezar.economic_timer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


//long savedValue = 0l;
//SharedPreferences someData;

public class HistoryActivity extends TimerActivity{

    TextView textView;
    Toolbar toolbar_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbar_h = (Toolbar) findViewById(R.id.toolbar_history);
        setSupportActionBar(toolbar_h);
        /*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("Timer");
            //The key argument here must match that used in the other activity
        }*/

        textView = (TextView) findViewById(R.id.textView);

        long value = getIntent().getIntExtra("Timer", 0);

        //TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setText(String.valueOf(value));
        //setContentView(value);

        //Intent intent = getIntent();
        //String value = intent.getStringExtra("Timer");
        //The key argument here must match that used in the other activity


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.id_language_h) {
            Toast.makeText(this, "Language is clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.id_logout_h) {
            Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
            startActivity(intent);
        }else if (id == R.id.id_timer_h){
            Intent intent2 = new Intent(HistoryActivity.this, TimerActivity.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}
