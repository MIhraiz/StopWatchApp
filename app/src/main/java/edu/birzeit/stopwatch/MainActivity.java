package edu.birzeit.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInstance(savedInstanceState);
        runTimer();

    }

    private void checkInstance(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("SECONDS");
            running = savedInstanceState.getBoolean("RUNNING");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SECONDS", seconds);
        outState.putBoolean("RUNNING", running);

    }

    private void runTimer() {
        final TextView txtTime = findViewById(R.id.txtClock);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    seconds++;
                }
                int hours = seconds/3600;
                int minutes = (seconds%3600) / 60;
                int secs = seconds%60;
                String time = String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", secs);
                txtTime.setText(time);
                handler.postDelayed(this,1000);
            }
        } );

    }

    public void btnResetOnClick(View view) {
        running = false;
        seconds = 0;
    }

    public void btnStopOnClick(View view) {
        running = false;
    }

    public void btnStartOnClick(View view) {
        running = true;
    }
}