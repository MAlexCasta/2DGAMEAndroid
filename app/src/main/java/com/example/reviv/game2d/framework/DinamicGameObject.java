package com.example.reviv.game2d.framework;

import com.example.reviv.game2d.framework.math.Vector2;

public class DinamicGameObject extends GameObject{
    public final Vector2 velocity;
    public final Vector2 accel;

    public DinamicGameObject(float x,float y,float width,float height)
    {
        super(x, y, width, height);
        velocity=new Vector2();
        accel=new Vector2();
    }
}
