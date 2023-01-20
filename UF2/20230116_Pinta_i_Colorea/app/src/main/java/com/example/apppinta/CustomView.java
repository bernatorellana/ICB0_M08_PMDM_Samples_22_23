package com.example.apppinta;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private PointF punt1, punt2;
    private Paint p;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        punt1 = new PointF(  0.75f*getWidth(), getHeight()*0.666f);
        punt2 = new PointF(  0.75f*getWidth(), getHeight()*0.666f);
        //-------------------------------
        p = new Paint();
        p.setColor(Color.YELLOW);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("XXX", "onDraw: ");
        canvas.drawColor(Color.RED);
        canvas.drawRect(0,0,100,100,p);

        canvas.drawPoint(300,300,p);
       //update(canvas, 200000);
        int w = getWidth();
        int h = getHeight();
        canvas.drawLine(0,0, w, h, p);
        int marge = 10;
        canvas.drawLines( new float[]{  marge,marge, // primer punt
                                        w -marge, marge,  // segon punt
                                        w -marge, marge,  // segon punt
                                        w -marge, h -marge,  // tercer punt
                                        w -marge, h -marge,  // tercer punt
                                        marge, h -marge,
                                        marge, h -marge,
                                        marge,marge
                                }, // quart punt
                        p );
        Paint p_linia = new Paint();
        p_linia.setStyle(Paint.Style.STROKE);
        p_linia.setColor(Color.argb(255,100,0,100));
        p_linia.setStrokeWidth(50);
        marge+=p_linia.getStrokeWidth()/2;
        canvas.drawOval(marge,marge, w -marge, h -marge,p_linia );

        Path path = new Path();
        path.moveTo(w /2, h /2);
        path.lineTo(w, 0);
        path.cubicTo(   punt2.x, punt2.y,
                        punt1.x, punt1.y,
                        //0.75f*w,h*0.666f,
                            w,h);
        canvas.drawPath(path, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int dits = event.getPointerCount();
        punt1.x =  event.getX(0);
        punt1.y = event.getY(0);
        if(dits>1){
            punt2.x =  event.getX(1);
            punt2.y = event.getY(1);
        }
        invalidate();// repinta la view
        return true;
    }

    void update(Canvas c, int iteracions) {

        double x=0,y=0;

        for(int i=0;i<iteracions;i++) {
            double nextX, nextY;
            double r = Math.random();
            if (r < 0.01) {
                nextX = 0;
                nextY = 0.16 * y;
            } else if (r < 0.86) {
                nextX = 0.85 * x + 0.04 * y;
                nextY = -0.04 * x + 0.85 * y + 1.6;
            } else if (r < 0.93) {
                nextX = 0.20 * x - 0.26 * y;
                nextY = 0.23 * x + 0.22 * y + 1.6;
            } else {
                nextX = -0.15 * x + 0.28 * y;
                nextY = 0.26 * x + 0.24 * y + 0.44;
            }

            // Scaling and positioning
            double plotX = c.getWidth() * (x + 3) / 6;
            double plotY = c.getHeight() - c.getHeight() * ((y + 2) / 14);

            c.drawPoint((float) plotX, (float) plotY, p);
            x = nextX;
            y = nextY;
        }

    }




}
