package com.example.reseller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splach_screen extends AppCompatActivity
{
    Animation top_animation ,bottom_animation;
    ImageView image;
    TextView reseller;
    private  static  int SPLASH_SCREEN = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
     //   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splach_screen);



        top_animation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_animation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image= findViewById(R.id.imageView);
        reseller=findViewById(R.id.splash_text);
        image.setAnimation(top_animation);
        reseller.setAnimation(bottom_animation);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(splach_screen.this,Testing.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
