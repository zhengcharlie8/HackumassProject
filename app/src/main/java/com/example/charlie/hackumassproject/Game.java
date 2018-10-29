package com.example.charlie.hackumassproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity {
    int score = 0;
    public double delay = 1000;
    int MIN_DELAY = 200;
    MediaPlayer mediaPlayer;
    int topScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
         mediaPlayer = MediaPlayer.create(this, R.raw.music);
        Button button1 = findViewById(R.id.radioButton);
        startTimer();
        mediaPlayer.start();
        int s= getIntent().getIntExtra("Score",0);
        topScore = s;
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addScore();
                Button toSend = findViewById(R.id.radioButton);
                animateFade(toSend);
            }
        });
        Button button2 = findViewById(R.id.radioButton2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addScore();
                Button toSend = findViewById(R.id.radioButton2);
                animateFade(toSend);
            }
        });
    }
    public void startTimer(){
        final Timer t=new Timer();
        final ProgressBar bar = findViewById(R.id.progressBar);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                bar.incrementProgressBy(-1*(score/10)+-1);
                if(bar.getProgress() == 0){
                    t.cancel();
                    mediaPlayer.stop();
                    finish();
                    startActivity(new Intent(Game.this, EndScreen.class).putExtra("Score", score).putExtra("TopScore",topScore));
                }
            }
        }, 0, 100L);
    }
    public void addScore(){
        TextView scoreboard = findViewById(R.id.textView);
        scoreboard.setText("Score: " + ++score);
        final ProgressBar bar = findViewById(R.id.progressBar);
        bar.incrementProgressBy(7);
        if (delay > MIN_DELAY)
        delay = delay*.94;
        }
    public void animateFade(Button circle){
        final Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        circle.startAnimation(fadeOut);
        Handler handler = new Handler();
        final Button button = circle;
        Runnable run = new Runnable()
        {
            public void run() {
                button.setX((float)Math.random()*800);
                button.setY((float)Math.random()*1200+900);
                button.startAnimation(fadeIn);
            }
        };
        handler.postDelayed(run,(long)delay);
    }
}