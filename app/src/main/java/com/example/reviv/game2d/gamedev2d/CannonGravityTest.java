package com.example.reviv.game2d.gamedev2d;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.FloatMath;


import com.example.reviv.game2d.framework.Game;
import com.example.reviv.game2d.framework.Input;
import com.example.reviv.game2d.framework.Screen;
import com.example.reviv.game2d.framework.gl.Vertices;
import com.example.reviv.game2d.framework.impl.GLGame;
import com.example.reviv.game2d.framework.impl.GLGraphics;
import com.example.reviv.game2d.framework.math.Vector2;

public class CannonGravityTest extends GLGame {
	public Screen getStartScreen() {
		return new CannonGravityScreen(this);
	}
	
	class CannonGravityScreen extends Screen {
	    float FRUSTUM_WIDTH = 9.6f;
	    float FRUSTUM_HEIGHT = 6.4f;
	    GLGraphics glGraphics;
	    Vertices cannonVertices;
	    Vertices ballVertices;
	    Vector2 cannonPos = new Vector2(FRUSTUM_WIDTH/2,0);
	    float cannonAngle = 90;
	    Vector2 touchPos = new Vector2();
	    Vector2 ballPos = new Vector2(0,0);
	    Vector2 ballVelocity = new Vector2(0,0);            
	    Vector2 gravity = new Vector2(0,-10);

	    public CannonGravityScreen(Game game) {
	        super(game);
	        glGraphics = ((GLGame) game).getGlGraphics();
	        cannonVertices = new Vertices(glGraphics, 3, 0, false, false);
	        cannonVertices.setVertices(new float[] { -0.5f, -0.5f, 
	                                            0.5f, 0.0f, 
	                                           -0.5f, 0.5f }, 0, 6);
	        ballVertices = new Vertices(glGraphics, 4, 6, false, false);
	        ballVertices.setVertices(new float[] { -0.1f, -0.1f,
	                                                0.1f, -0.1f,
	                                                0.1f,  0.1f,
	                                               -0.1f,  0.1f }, 0, 8);
	        ballVertices.setIndices(new short[] {0, 1, 2, 2, 3, 0}, 0, 6);                                                
	    }

	    @Override
	    public void update(float deltaTime) {
	        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
	        game.getInput().getKeyEvents();

	        int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            Input.TouchEvent event = touchEvents.get(i);

	            touchPos.x = (event.x / (float) glGraphics.getWidth())
	                    * FRUSTUM_WIDTH;
	            touchPos.y = (1 - event.y / (float) glGraphics.getHeight())
	                    * FRUSTUM_HEIGHT;
	            cannonAngle = touchPos.sub(cannonPos).angle();                       
	            
	            if(event.type == Input.TouchEvent.TOUCH_UP) {
	                float radians = cannonAngle * Vector2.TO_RADIANS;
	                float ballSpeed = touchPos.len()*1.8f;
	                ballPos.set(cannonPos);
	                ballVelocity.x = (float)Math.cos(radians) * ballSpeed;
	                ballVelocity.y = (float)Math.sin(radians) * ballSpeed;
	            }
	        }
	                                      
	        ballVelocity.add(gravity.x * deltaTime, gravity.y * deltaTime);
	        ballPos.add(ballVelocity.x * deltaTime, ballVelocity.y * deltaTime);
	    }

	    @Override
	    public void present(float deltaTime) {

	        GL10 gl = glGraphics.getGl();
	        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
	        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        gl.glMatrixMode(GL10.GL_PROJECTION);
	        gl.glLoadIdentity();
	        gl.glOrthof(0, FRUSTUM_WIDTH, 0, FRUSTUM_HEIGHT, 1, -1);
	        gl.glMatrixMode(GL10.GL_MODELVIEW);
	        
	        gl.glLoadIdentity();
	        gl.glTranslatef(cannonPos.x, cannonPos.y, 0);
	        gl.glRotatef(cannonAngle, 0, 0, 1);
	        gl.glColor4f(255,0,0,1);
	        cannonVertices.bind();
	        cannonVertices.draw(GL10.GL_TRIANGLES, 0, 3);
	        cannonVertices.unbind();
	        
	        gl.glLoadIdentity();
	        gl.glTranslatef(ballPos.x, ballPos.y, 0);
	        gl.glColor4f(1,0,0,1);
	        ballVertices.bind();
	        ballVertices.draw(GL10.GL_TRIANGLES, 0, 6);
	        ballVertices.unbind();
	    }

	    @Override
	    public void pause() {

	    }

	    @Override
	    public void resume() {

	    }

	    @Override
	    public void dispose() {

	    }
	}
}
