package com.example.reviv.game2d.framework.math;

public class Vector2 {
    public static float TO_RADIANS=(1/180.0f)*(float)Math.PI;
    public static float TO_DEGREES=(1/(float)Math.PI)*180;
    public float x, y;

    public  Vector2()
    {

    }
    public  Vector2(float x,float y)
    {
        this.x=x;
        this.y=y;
    }
    public Vector2(Vector2 otro)
    {
        this.x=otro.x;
        this.y=otro.y;
    }
    public Vector2 cpy()
    {
        return new Vector2(x,y);
    }
    public  Vector2 set(float x,float y)
    {
        this.x=x;
        this.y=y;
        return this;
    }
}
