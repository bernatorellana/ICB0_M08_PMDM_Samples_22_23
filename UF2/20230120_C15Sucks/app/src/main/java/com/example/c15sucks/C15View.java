package com.example.c15sucks;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class C15View extends View implements ViewTreeObserver.OnGlobalLayoutListener {


    private enum ESTAT {
        E_JOC_NO_INICIAT,
        E_EN_JOC_ESPERANT_PUNT_INICI,
        E_EN_JOC_TRACANT_CAMI,
        E_EN_JOC_GAME_OVER,
        E_EN_JOC_YOU_WIN
    }
    //-------------------------------------------
    private ESTAT mEstat = ESTAT.E_JOC_NO_INICIAT;
    private Bitmap mBackground;
    private Bitmap mTrack;
    private Bitmap mMask;
    private IGameListener mListener;
    private Paint p, pCami;
    private Path cami;
    private String mMissatgeIntern;
    //-------------------------------------------

    public C15View(Context context, IGameListener listener) {
        this(context,listener, null);
    }

    public C15View(Context context, IGameListener listener, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mListener = listener;
        p = new Paint();
        //================= Pintura pel camí ======================
        pCami = new Paint();
        pCami.setStyle(Paint.Style.STROKE);
        pCami.setStrokeWidth(10);
        pCami.setColor(Color.RED);
        //========================================================
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
        if(mMissatgeIntern!=null){
            // això no va bé....text massa petit
            canvas.drawText(mMissatgeIntern, 30,40, pCami);
        }
        switch (mEstat) {
            case E_EN_JOC_TRACANT_CAMI: {

                if(cami!=null) {
                    canvas.drawPath(cami, pCami);
                }
            }
            break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int c = mMask.getPixel((int) event.getX(), (int) event.getY());
        int r = Color.red(c);
        int g = Color.green(c);
        int b = Color.blue(c);

        switch (mEstat) {
            case E_EN_JOC_TRACANT_CAMI: {
                if(event.getActionMasked()==1){
                    //aixeca el dit !
                    setEstat(ESTAT.E_EN_JOC_GAME_OVER);
                } else {
                    // el dit continua a lloc
                    if (r + b + g == 0) { // negre com la nit
                        //mListener.onMessage("OUCH!");
                        setEstat(ESTAT.E_EN_JOC_GAME_OVER);
                    }
                    else if ( b + g == 0 && r==255) { // vermell
                        //arribem a meta
                        setEstat(ESTAT.E_EN_JOC_YOU_WIN);
                    }
                    else {
                        cami.lineTo(event.getX(), event.getY());
                        invalidate();
                    }
                }
            } break;
            //--------------------------------------
            case E_EN_JOC_ESPERANT_PUNT_INICI: {
                if (r + b == 0 && g == 255) {
                    cami = new Path();
                    cami.moveTo(event.getX(), event.getY());
                    setEstat(ESTAT.E_EN_JOC_TRACANT_CAMI);
                    invalidate();
                } else {
                    Log.d("XXX", "onTouchEvent: "+event.getActionMasked());
                    if(event.getActionMasked()== 0/*MotionEvent.ACTION_POINTER_DOWN*/){
                        mMissatgeIntern = "RUC!";
                        invalidate();
                    }
                }
            } break;
            //--------------------------------------
            case E_EN_JOC_GAME_OVER: setEstat(ESTAT.E_JOC_NO_INICIAT);break;
            //--------------------------------------
            case E_EN_JOC_YOU_WIN:setEstat(ESTAT.E_JOC_NO_INICIAT);break;
            //--------------------------------------
            case E_JOC_NO_INICIAT: setEstat(ESTAT.E_EN_JOC_ESPERANT_PUNT_INICI);break;


        }


        return true;
    }

    private void setEstat(ESTAT e) {
        switch(e) {
            case E_EN_JOC_GAME_OVER: {
                mListener.onMessage("GAME OVER");
                Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);
                mListener.onGameIsOver();
            }break;
            case E_EN_JOC_ESPERANT_PUNT_INICI:{
                cami = null;
                mListener.onMessage("Ves al punt d'inici per començar a jugar");
            }break;
            case E_EN_JOC_TRACANT_CAMI:{
                mListener.onMessage("E_EN_JOC_TRACANT_CAMI");
            }break;
            case E_EN_JOC_YOU_WIN:{
                mListener.onMessage("YOU WIN");
                mListener.onPlayerWin();
            } break;
            case E_JOC_NO_INICIAT: {
                cami = null;
                mListener.onMessage("E_JOC_NO_INICIAT");
            }break;
        }
        mEstat = e;
    }

    private void pinta(Bitmap bitmap, @DrawableRes int idRes) {
        Bitmap b = BitmapFactory.decodeResource(getResources(), idRes);
        Canvas cBack = new Canvas(bitmap);
        cBack.drawBitmap(b,null,
                new Rect(0,0,getWidth(), getHeight()),
                new Paint());
    }
}
