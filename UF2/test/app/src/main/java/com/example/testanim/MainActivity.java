package com.example.testanim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private SpringAnimation animX,animY;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         img = findViewById(R.id.imgPlane);

        animY = new SpringAnimation(img,
                DynamicAnimation.TRANSLATION_Y);
        animX = new SpringAnimation(img,
                DynamicAnimation.TRANSLATION_X);
        SpringForce force = new SpringForce();
        force.setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        force.setStiffness(SpringForce.STIFFNESS_LOW);
        animX.setSpring(force);
        animY.setSpring(force);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);
        Log.d("TAG", "img: "+img.getX()+";"+img.getY());
        Log.d("TAG", "onTouchEvent: "+event.getX()+";"+event.getY());
        animX.animateToFinalPosition(event.getX());
        animY.animateToFinalPosition(event.getY());
        return true;

    }
}