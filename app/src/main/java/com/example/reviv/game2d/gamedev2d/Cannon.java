package com.example.reviv.game2d.gamedev2d;

import com.example.reviv.game2d.framework.GameObject;

public class Cannon extends GameObject {
    public float angle;

    public Cannon(float x,float y,float width,float height)
    {
        super(x, y, width, height);
        angle=0;
    }
}
