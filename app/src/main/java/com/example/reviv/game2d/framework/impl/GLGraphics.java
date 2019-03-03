package com.example.reviv.game2d.framework.impl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

public class GLGraphics {
    GLSurfaceView glView;
    GL10 gl;

    GLGraphics(GLSurfaceView glView)
    {
        this.glView=glView;
    }
    public GL10 getGl()
    {
        return gl;
    }
    void setGl(GL10 gl)
    {
        this.gl=gl;
    }
    public int getWidth()
    {
        return glView.getWidth();
    }
    public int getHeight()
    {
        return glView.getHeight();
    }
}