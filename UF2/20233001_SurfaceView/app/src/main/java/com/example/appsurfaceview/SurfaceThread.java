package com.example.appsurfaceview;

import android.graphics.Canvas;

public class SurfaceThread extends Thread{


    private static final int FPS = 60; // frames per second
    private int tempsFrame_Ns;
    private DemoSurfaceView mView;
    private boolean esticViu;

    SurfaceThread(DemoSurfaceView view){
        mView = view;
        esticViu = true;
        tempsFrame_Ns =  1000000000/FPS;
    }

    @Override
    public void run() {
        super.run();
        long tempsFrame;
        long tempsIniciFrame;
        while(esticViu){
            Canvas canvas = mView.getHolder().lockCanvas();
            if(canvas!=null){
                tempsIniciFrame = System.nanoTime();
                mView.tick();
                mView.onPaintCasola(canvas);
                tempsFrame = System.nanoTime() - tempsIniciFrame;
                if(tempsFrame<tempsFrame_Ns) {
                    try {
                        Thread.sleep((int)((tempsFrame_Ns-tempsFrame)/1000000));
                    } catch (InterruptedException e) {}
                }
                mView.getHolder().unlockCanvasAndPost(canvas); // !IMPORTANT
            }
        }
    }

    public void muerete() {
        esticViu = false;
    }
}
