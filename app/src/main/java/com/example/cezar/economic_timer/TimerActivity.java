package com.example.cezar.economic_timer;



import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;



public class TimerActivity extends AppCompatActivity {
    Toolbar toolbar;

    private ImageButton imgbtnPause;
    private ImageButton imgbtnReset;
    private ImageButton imgbtnStart;
    private ImageButton imgbtnStop;
    private Chronometer mChronometer;


    public long saveTime;
    private long lastPause;

    public static String filename = "MySharedString";
    SharedPreferences someData;
    protected long time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgbtnPause = (ImageButton) findViewById(R.id.imgButtonPause);
        imgbtnReset = (ImageButton) findViewById(R.id.imgButtonReset);
        imgbtnStart = (ImageButton) findViewById(R.id.imgButtonStart);
        imgbtnStop = (ImageButton) findViewById(R.id.imgButtonStop);

        mChronometer = (Chronometer) findViewById(R.id.chronometer);

        //someData = getSharedPreferences(filename, 0);
        //long savedValue = someData.getLong(filename, 0);
        //final Editor editor = someData.edit();

        mChronometer.setText("00:00:00");
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                CharSequence text = chronometer.getText();
                if (text.length() == 5) {
                    chronometer.setText("00:" + text);
                } else if (text.length() == 7) {
                    chronometer.setText("0" + text);
                }
            }
        });

        imgbtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPause != 0) {
                    mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                } else {
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }
                mChronometer.start();
                imgbtnStart.setEnabled(false);
                imgbtnPause.setEnabled(true);
            }
        });

        imgbtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPause = SystemClock.elapsedRealtime();
                mChronometer.stop();
                imgbtnPause.setEnabled(false);
                imgbtnStart.setEnabled(true);
            }
        });

        imgbtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;
                imgbtnStart.setEnabled(true);
                imgbtnPause.setEnabled(false);
            }
        });

        imgbtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.stop();
                final AlertDialog.Builder builder = new AlertDialog.Builder(TimerActivity.this);
                builder.setTitle("Stop alert");
                builder.setMessage("Have you finished working mate?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mChronometer.stop();
                        mChronometer.setBase(SystemClock.elapsedRealtime());
                        imgbtnStart.setEnabled(true);
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(TimerActivity.this);
                        builder1.setTitle("Save");
                        builder1.setMessage("Do you want to save your progress mate?");
                        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                //Intent bundleint = new Intent()
                                saveTime = mChronometer.getBase();
                                //long seconds = (int)(saveTime);
                                Intent i = new Intent(TimerActivity.this, HistoryActivity.class);
                                i.putExtra("Timer", saveTime);
                                startActivity(i);

                                mChronometer.stop();
                                mChronometer.setBase(SystemClock.elapsedRealtime());
                                imgbtnStart.setEnabled(true);
                            }
                        });
                        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mChronometer.start();
                                imgbtnStart.setEnabled(false);
                                imgbtnPause.setEnabled(true);
                            }
                        });
                        builder1.create();
                        builder1.show();
                    }
                });
                builder.setNegativeButton("NO!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mChronometer.start();
                        imgbtnStart.setEnabled(false);
                        imgbtnPause.setEnabled(true);
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.id_language) {
            Toast.makeText(this, "Language is clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.id_logout) {
            Intent intent = new Intent(TimerActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.id_history) {
            Intent intent2 = new Intent(TimerActivity.this, HistoryActivity.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}
    /*
    public class FireMissilesDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.dialog_fire_missiles)
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }*/

