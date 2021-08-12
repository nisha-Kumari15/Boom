package com.example.boom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textViewInfo, textViewHighest, textViewMyScore;
    Button buttonPlayAgain, buttonQuit;

    int myScore;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewHighest = findViewById(R.id.textViewHighest);
        textViewInfo = findViewById(R.id.textViewInfo);
        textViewMyScore = findViewById(R.id.textViewMyScore);
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        buttonQuit = findViewById(R.id.buttonQuit);

        myScore = getIntent().getIntExtra("score",0);
        textViewMyScore.setText("Your score : " +myScore);

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore",0);

        if(myScore >= highestScore)
        {
            sharedPreferences.edit().putInt("highestScore",myScore).apply();
            textViewHighest.setText("Highest Score : "+myScore);
            textViewInfo.setText("Congratulations ! ");
        }
        else
        {
            textViewHighest.setText("Highest Score : "+highestScore);
            if((highestScore - myScore) > 10)
            {
                textViewInfo.setText("Fast !");
            }
            if((highestScore - myScore) > 3 && (highestScore - myScore) <= 10)
            {
                textViewInfo.setText("Good !");
            }
            if((highestScore - myScore) <= 3)
            {
                textViewInfo.setText("Bravo!!");
            }
        }
        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            }
        });
    }
}