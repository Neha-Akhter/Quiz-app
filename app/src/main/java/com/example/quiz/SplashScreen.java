package com.example.quiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER=8000;

    TextView textview;
    ImageView bg;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);





        textview=findViewById(R.id.tv);
        bg=findViewById(R.id.img);
        lottieAnimationView=findViewById(R.id.lottie);




        bg.animate().translationY(-2500).setDuration(2000).setStartDelay(8000);;
        textview.animate().translationY(1600).setDuration(2000).setStartDelay(8000);
        lottieAnimationView.animate().translationY(1400).setDuration(2000).setStartDelay(8000);;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();



            }
        },SPLASH_TIMER);

    }
}