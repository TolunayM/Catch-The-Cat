package com.tolunaymutlu.catchthecat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreView;
    TextView timeView;
    int score;
    ImageView imageView0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imgView;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreView = findViewById(R.id.scoreView);
        timeView = findViewById(R.id.timeView);
        score = 0;
        imgView = new ImageView[9];

        for(int i = 0; i<9; i++){
            String imageVID = "imageView" + i;
            int resID = getResources().getIdentifier(imageVID,"id",getPackageName());
            imgView[i] = findViewById(resID);

        }
        hideImage();consecutive
    }
    public void increaseScore(View view){
        if(score == 0){
            startTimer();
        }
        score++;
        scoreView.setText("Your Score: " + score);
    }
    public void finishAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Game Over");
        alert.setMessage("Do you want to play again ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Hurry Catch The Cat",Toast.LENGTH_LONG).show();
                score = 0;
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Kitten ran away from you because you didn't want to play again",Toast.LENGTH_LONG).show();
                handler.removeCallbacks(runnable);
                for(ImageView image : imgView){
                    image.setVisibility(View.INVISIBLE);
                }

            }
        });

        alert.show();


    }
    public void startTimer(){
        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText("Time "+millisUntilFinished/1000);
            }
            @Override
            public void onFinish(){
                finishAlert();
                Toast.makeText(getApplicationContext(), "Score: " + score, Toast.LENGTH_LONG).show();
            }
        }.start();

    }
    public void hideImage(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imgView){
                    image.setVisibility(View.INVISIBLE);
                }

                //TODO Gelen randomlari oncekinden farkli yap
                Random randomImage = new Random();
                int i = randomImage.nextInt(9);
                imgView[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);


    }

}
