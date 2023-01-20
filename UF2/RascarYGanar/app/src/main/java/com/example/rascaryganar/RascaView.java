package com.example.rascaryganar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

public class RascaView extends View implements ViewTreeObserver.OnGlobalLayoutListener {

    Paint p, p_puntogordo;
    Bitmap bRasca;
    Canvas cRasca;

    public RascaView(Context context) {
        this(context, null);
    }

    public RascaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p_puntogordo = new Paint();
        p_puntogordo.setStyle(Paint.Style.FILL_AND_STROKE);
        p_puntogordo.setStrokeWidth(100);
        p_puntogordo.setColor(Color.TRANSPARENT);
        p_puntogordo.setBlendMode(BlendMode.CLEAR);

        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        bRasca = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        cRasca = new Canvas(bRasca);
        cRasca.drawColor(Color.GREEN);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //cRasca.drawPoint(event.getX(), event.getY(), p_puntogordo);
        int radi = 40;
        cRasca.drawOval(
                event.getX()-radi, event.getY()-radi,
                event.getX()+radi, event.getY()+radi,
                p_puntogordo);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);

        Bitmap mario = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        Rect mida = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawBitmap(mario, null, mida, p);
        canvas.drawBitmap(bRasca, null, mida, p);
    }
}

