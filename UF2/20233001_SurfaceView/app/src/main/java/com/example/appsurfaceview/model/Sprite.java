package com.example.appsurfaceview.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import androidx.annotation.IdRes;

import com.example.appsurfaceview.DemoSurfaceView;
import com.example.appsurfaceview.R;

import java.util.ArrayList;
import java.util.List;

public abstract class Sprite extends GameObject {

    protected Point mPosicio;
    private int mSpriteActiu;

    private List<Bitmap> mBitmapSprites;
    private List<Integer> mBitmapFrames;
    private int mFrameActiu = 0;
    private int mWidth, mHeight;
    private int mDelay =0, mDelayRandom =0;
    private PointF mDireccio = new PointF(1,0);
    private PointF[] mDireccions = new PointF[] {
            new PointF(1,0),
            new PointF(-1,0),
            new PointF(0,1),
            new PointF(0,-1)
    };
    //----------------------------------------------------------
    public Sprite(DemoSurfaceView view, int width, int height ) {
        super(view);
        mWidth = width;
        mHeight = height;
        mPosicio = new Point(40,40);

        mBitmapSprites = new ArrayList<>();
        mBitmapFrames  = new ArrayList<>();

        mSpriteActiu = 1;
        for( int idRes: getSprites()){
            Bitmap m = BitmapFactory.decodeResource( view.getResources() ,idRes);
            mBitmapFrames.add(m.getWidth()/mWidth);
            mBitmapSprites.add(m);
        }
    }

    protected void setSpriteActiu(int idxSprite){
        if(idxSprite<0 || idxSprite>mBitmapFrames.size()-1)
            throw new RuntimeException("Animal! on vas ! No tinc tants sprites");
        this.mSpriteActiu = idxSprite;
    }

    public abstract List<Integer> getSprites();

    /**
     *
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas) {

        Rect src = new Rect(mWidth*(mFrameActiu),0, mWidth*(mFrameActiu+1), mHeight);
        Rect dst = new Rect(
                     mPosicio.x,              mPosicio.y,
                mPosicio.x+mWidth, mPosicio.y+mHeight);
        canvas.drawBitmap(mBitmapSprites.get(mSpriteActiu),
                src, dst, new Paint()
                )   ;
    }


    @Override
    public void tick() {
        mDelay = (mDelay +1) %5;
        if(mDelay ==0) {
            mFrameActiu = (mFrameActiu + 1) % mBitmapFrames.get(mSpriteActiu);
        }
    }
}
