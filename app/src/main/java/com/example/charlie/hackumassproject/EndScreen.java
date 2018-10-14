package com.example.charlie.hackumassproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {
    int topScore= 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        int s= getIntent().getIntExtra("Score",0);
        int newtop= getIntent().getIntExtra("TopScore",0);
        if (s>newtop)
            newtop = s;
        TextView score = findViewById(R.id.score);
        if (newtop > topScore)
            topScore = newtop;
        score.setText("Your Score: " + s);
        TextView top = findViewById(R.id.topScore);
        top.setText("Top Score: " + topScore);
        Button button = findViewById(R.id.Try_Again);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EndScreen.this, Game.class).putExtra("Score", topScore));
                finish();
            }
        });
    }

}