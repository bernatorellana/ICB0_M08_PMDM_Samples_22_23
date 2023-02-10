package com.example.appsurfaceview.model;

import android.graphics.Canvas;

import com.example.appsurfaceview.DemoSurfaceView;

public abstract class GameObject {

    protected DemoSurfaceView mView;

    public GameObject(DemoSurfaceView view) {
        mView = view;
    }

    public abstract void onDraw(Canvas canvas);

    public abstract void tick();

}
