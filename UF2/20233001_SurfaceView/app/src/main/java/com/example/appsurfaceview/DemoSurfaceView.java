package com.example.appsurfaceview;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.appsurfaceview.model.Bola;
import com.example.appsurfaceview.model.GameObject;
import com.example.appsurfaceview.model.Ghost;
import com.example.appsurfaceview.model.MovimentJoystick;
import com.example.appsurfaceview.model.Sprite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceThread mThread;

    private SimpleDateFormat mFormatter;
    private Paint mPaint;
    //------------------------------------------
    List<GameObject> mGameObjects;
    //------------------------------------------

    public DemoSurfaceView(Context context) {
        this(context, null);
    }

    public DemoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        getHolder().addCallback(this);
        mGameObjects = new ArrayList<>();
        mFormatter = new SimpleDateFormat("HH:mm:ss.SS");

    }

    public MovimentJoystick getMoviment(){
        return mMoviment;
    }
    PointF posicioDelDit;
    MovimentJoystick mMoviment;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if(event.getActionMasked() ==
                MotionEvent.ACTION_DOWN){
            posicioDelDit = new PointF(event.getX(),event.getY());
        } else if(event.getActionMasked()==
              MotionEvent.ACTION_UP){
            posicioDelDit = null;
            mMoviment = null;
        } else {
                // el dit s'està arrossegant per la pantalla
            float x = event.getX() - posicioDelDit.x;
            float y = event.getY() - posicioDelDit.y;
            mMoviment = new MovimentJoystick(
                    (int)Math.signum(x)* ( Math.abs(x)>Math.abs(y)?1:0 ),
                    (int)Math.signum(y)* ( Math.abs(x)<Math.abs(y)?1:0 )
            );
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        for(int x=0;x<getWidth();x+=getWidth()/10) {
            Bola b = new Bola(this, new Point(x, x),
                    (int) (getWidth() * 0.07), new PointF(10, 10),
                    getWidth(), getHeight());
            mGameObjects.add(b);
        }

        mGameObjects.add(new Ghost(this,53,66));
        //--------------------------

        mThread = new SurfaceThread(this);
        mThread.start(); // engega el fil
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if(mThread!=null){
            mThread.muerete();
        }
    }

    public void onPaintCasola(Canvas canvas) {
        //Log.d("XX", "onPaintCasola: ");
        canvas.drawColor(Color.BLUE);
        Date now = new Date();
        String text = mFormatter.format(now);
        UIUtils.setTextSizeForWidth(mPaint, 600, text);
        canvas.drawText( text, 100,200, mPaint);
        for(GameObject b: mGameObjects){
            b.onDraw(canvas);
        }

    }

    public void tick() {
        for(GameObject b: mGameObjects){
            b.tick();
        }
    }
}
