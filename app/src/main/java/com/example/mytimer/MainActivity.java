package com.example.mytimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final long TIMER_START = 0;

    CountDownTimer countDownTimer;
    Boolean timerRunning ;
    private long timeLeft = TIMER_START;

    Button start , reset , increment , decrement;
    TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();

    }
    private void initializer (){
        start = findViewById(R.id.bt_start_pause);
        reset = findViewById(R.id.bt_reset);
        increment = findViewById(R.id.bt_increment);
        decrement = findViewById(R.id.bt_decrement);
        timer=findViewById(R.id.timer);

        start.setOnClickListener(this);
        reset.setOnClickListener(this);
        decrement.setOnClickListener(this);
        increment.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bt_start_pause:
                if (timerRunning){
                    pauseTimer();
                }else {
                    startTimer();
                }

                break;

            case R.id.bt_reset:

                resetTimer();
                updateCountDownText();

                break;

            case R.id.bt_increment:


                break;

            case R.id.bt_decrement:

                break;
        }

    }
    private void  pauseTimer(){

        countDownTimer.cancel();
        timerRunning=false;
        start.setText("Start");
        reset.setVisibility(View.VISIBLE);



    }
    private void  startTimer(){
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft =millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timerRunning=false;
                start.setText("Start");
                start.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);

            }
        }.start();

        timerRunning=true;
        start.setText("Pause");
        reset.setVisibility(View.INVISIBLE);


    }
    private void  resetTimer(){
        timeLeft = TIMER_START;
        updateCountDownText();
        reset.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);


    }
    public void updateCountDownText(){
        int minutes = (int) (timeLeft / 1000)/60;
        int seconds = (int) (timeLeft / 1000)%60;
        String timeLeftFormatted = String.format(Locale.getDefault(),
                "%02d:%02d",minutes,seconds);

        timer.setText(timeLeftFormatted);


    }
}
