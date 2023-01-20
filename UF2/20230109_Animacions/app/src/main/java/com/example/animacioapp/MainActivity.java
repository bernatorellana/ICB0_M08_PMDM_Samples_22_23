package com.example.animacioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements
        Animator.AnimatorListener, ViewTreeObserver.OnGlobalLayoutListener {

    private Button btnMoume;
    private boolean animacioActiva = false;
    private ObjectAnimator anim, animX, animY;
    private static final String TAG = "XXX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoume = findViewById(R.id.btnMoume);
        //Definim animació
        //btnMoume.setRotationX(45.0f);

        btnMoume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(anim!=null && anim.isRunning()) return;
                iniciAnimacio();
            }
        });

        // Ens enregistrem a un esdeveniment que ens avisa
        // quan ha fet el layout de la pàgina i ja podem
        // consultar les dimensions (amplada i alçada)
        ConstraintLayout frm = findViewById(R.id.frm);
        frm.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
    @Override
    public void onGlobalLayout() {

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.mario);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you


        ImageView imvMario = findViewById(R.id.imvMario);
        AnimationDrawable animator = (AnimationDrawable) imvMario.getDrawable();
        animator.start();

        Rect rec = getDimensionsFinestra();
        ImageView imgEsquerra = findViewById(R.id.imgEsquerra);
        ImageView imgDreta = findViewById(R.id.imgDreta);

        Log.d(TAG, "onCreate: "+imgDreta.getWidth());


        ObjectAnimator animXLeftAlpha = ObjectAnimator.ofFloat(imgEsquerra, "Alpha", 0);
        ObjectAnimator animXRightAlpha = ObjectAnimator.ofFloat(imgDreta, "Alpha", 0);

        ObjectAnimator animXLeft = ObjectAnimator.ofFloat(imgEsquerra, "X", (float)-imgEsquerra.getWidth());
        ObjectAnimator animYRight = ObjectAnimator.ofFloat(imgDreta, "X", (float)imgDreta.getWidth()*2);
        long t = 6000;
        animXLeft.setDuration(t);
        animYRight.setDuration(t);
        animXLeftAlpha.setDuration(t/2);
        animXRightAlpha.setDuration(t/2);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animXLeft,animYRight, animXLeftAlpha, animXRightAlpha);
        set.start();

    }

    private void iniciAnimacio(){

        anim =  ObjectAnimator.ofFloat(
                btnMoume,"RotationX",
                btnMoume.getRotationX()+360);
        anim.setDuration(200);// milisegons



        //Obtenim les dimensions útils de la finestra
        Rect rec = getDimensionsFinestra();
        double destiX =  Math.random()*(rec.width()-btnMoume.getWidth());
        double destiY =  Math.random()*(rec.height()-btnMoume.getHeight());


        double distancia = getDistancia(
                btnMoume.getX(), btnMoume.getY(),
                destiX, destiY
                );
        double velocitatPixelsPerSegon = 600;
        long t = (long)( 1000 * distancia / velocitatPixelsPerSegon );
        animX = ObjectAnimator.ofFloat(btnMoume, "X", (float)destiX);
        animY = ObjectAnimator.ofFloat(btnMoume, "Y", (float)destiY);
        animX.setDuration(t);
        animY.setDuration(t);
        animX.addListener(MainActivity.this);
        AnimatorSet setGeneral = new AnimatorSet();
        AnimatorSet setTranslacio = new AnimatorSet();
        setTranslacio.playTogether(animX,animY);
        setGeneral.playSequentially(anim, setTranslacio);
        setGeneral.start();

    }

    private double getDistancia(float x, float y, double destiX, double destiY) {
        return Math.sqrt(Math.pow (destiY-y,2) + Math.pow(destiX-x,2));
    }

    private Rect getDimensionsFinestra() {
        Rect rect = new Rect();
        MainActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        // Calculem les dimensions de la toolbar superior
        int contentViewTop = MainActivity.this.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        // Restem l'alçada de la finestra de la toolbar
        int alsada = Math.abs(rect.height() - contentViewTop);
        return new Rect(0,0,rect.width(), alsada);
    }
    @Override
    public void onAnimationStart(Animator animator) {}

    @Override
    public void onAnimationEnd(Animator animator) {
        iniciAnimacio(); // torne-m'hi !
    }
    @Override
    public void onAnimationCancel(Animator animator) { }

    @Override
    public void onAnimationRepeat(Animator animator) { }



}