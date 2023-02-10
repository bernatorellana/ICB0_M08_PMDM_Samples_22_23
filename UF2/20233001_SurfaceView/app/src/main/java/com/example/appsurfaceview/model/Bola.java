package com.example.appsurfaceview.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import com.example.appsurfaceview.DemoSurfaceView;
import com.example.appsurfaceview.model.GameObject;

public class Bola extends GameObject {
    private Point coordPilota;
    private int radi;
    private PointF velocitatPilota;
    private Paint mPaintBola;
    private Paint mPaintBolaBrillo;
    private int width;
    private int height;

    public Bola(DemoSurfaceView view, Point coordPilota, int radi, PointF velocitatPilota, int width, int height) {
        super(view);
        this.coordPilota = coordPilota;
        this.radi = radi;
        this.velocitatPilota = velocitatPilota;

        mPaintBola = new Paint();
        mPaintBolaBrillo = new Paint();
        mPaintBola.setColor(Color.argb(255,220,220,220));
        mPaintBolaBrillo.setColor(Color.WHITE);

        this.height = height;
        this.width = width;
    }
    @Override
    public void onDraw(Canvas canvas) {
        //----------------------------------
        //Dibuixem bola
        canvas.drawOval(this.coordPilota.x-radi, this.coordPilota.y-radi,
                this.coordPilota.x+radi, this.coordPilota.y+radi, mPaintBola
        );
        float radiBrillo = radi * 0.4f;
        canvas.drawOval(this.coordPilota.x-1.5f*radiBrillo, this.coordPilota.y-1.5f*radiBrillo,
                this.coordPilota.x-0.5f*radiBrillo, this.coordPilota.y-0.5f*radiBrillo, mPaintBolaBrillo
        );
    }
    @Override
    public void tick(){
        // Actualitzem la bola
        float futuraX = coordPilota.x + velocitatPilota.x;
        float futuraY = coordPilota.y + velocitatPilota.y;
        if(futuraX>width-radi){

            float tXoc = (width-radi - coordPilota.x)/ (float)velocitatPilota.x;
            futuraX = width-radi;
            futuraY = coordPilota.y + velocitatPilota.y * tXoc;

            velocitatPilota.x *=-1*(0.5+Math.random());

        } else if(futuraX <radi) {
            float tXoc = (radi - coordPilota.x)/ (float)velocitatPilota.x;
            futuraX = radi;
            futuraY = coordPilota.y + velocitatPilota.y * tXoc;
            velocitatPilota.x *=-1*(0.5+Math.random());

        } else if(futuraY>height-radi )
        {
            float tXoc = (height-radi - coordPilota.y)/ (float)velocitatPilota.y;
            futuraY = height-radi;
            futuraX = coordPilota.x + velocitatPilota.x * tXoc;
            velocitatPilota.y *=-1*(0.5+Math.random());
        }
        else if(futuraY <radi)
        {
            float tXoc = (radi - coordPilota.y)/ (float)velocitatPilota.y;
            futuraY = radi;
            futuraX = coordPilota.x + velocitatPilota.x * tXoc;
            velocitatPilota.y *=-1*(0.5+Math.random());
        }
        {
            coordPilota.x = (int) futuraX;
            coordPilota.y = (int) futuraY;
        }
    }
}
