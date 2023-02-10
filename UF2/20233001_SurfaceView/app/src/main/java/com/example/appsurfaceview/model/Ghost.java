package com.example.appsurfaceview.model;

import com.example.appsurfaceview.DemoSurfaceView;
import com.example.appsurfaceview.R;

import java.util.ArrayList;
import java.util.List;

public class Ghost extends Sprite{
    public Ghost(DemoSurfaceView view, int width, int height) {
        super(view, width, height);
        modeEspantat(false);
    }

    public void modeEspantat(boolean esticCagat) {
        setSpriteActiu(esticCagat ? 1 : 0);
    }

    @Override
    public List<Integer> getSprites() {
        List<Integer> sprites = new ArrayList<>();
        sprites.add(R.drawable.red);
        sprites.add(R.drawable.blue);
        return sprites;
    }

    MovimentJoystick move = new MovimentJoystick(0,0);

    @Override
    public void tick() {
        super.tick();
        if(mView.getMoviment()!=null){
            move = mView.getMoviment();
        }
        this.mPosicio.x += move.x * 10;
        this.mPosicio.y += move.y * 10;

    }
}
