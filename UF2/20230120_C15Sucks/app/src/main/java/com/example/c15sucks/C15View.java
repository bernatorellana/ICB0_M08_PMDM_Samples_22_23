package com.example.c15sucks;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class C15View extends View implements ViewTreeObserver.OnGlobalLayoutListener {

    private Bitmap mBackground;
    private Bitmap mTrack;
    private Bitmap mMask;

    private Paint p;

    public C15View(Context context) {
        this(context, null);
    }

    public C15View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();

        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        // Creació dels bitmaps de la mateixa mida que la pantalla.
        mBackground = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mTrack = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mMask = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        //--------------------------------------------------------------
        pinta( mBackground, R.drawable.background);
        pinta( mMask, R.drawable.mascara);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBackground,0,0, p);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int c = mMask.getPixel((int)event.getX(),(int) event.getY());
        int r = Color.red(c);
        int g = Color.green(c);
        int b = Color.blue(c);

        if(r+b+g==0){ // negre com la nit
            Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
        }


        return true;
    }

    private void pinta(Bitmap bitmap, @DrawableRes int idRes) {
        Bitmap b = BitmapFactory.decodeResource(getResources(), idRes);
        Canvas cBack = new Canvas(bitmap);
        cBack.drawBitmap(b,null,
                new Rect(0,0,getWidth(), getHeight()),
                new Paint());
    }
}
