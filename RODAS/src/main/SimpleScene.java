package main;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class SimpleScene implements GLEventListener {
	
	private double theta = 0;
	private double s = 0;
	private double c = 0;

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		// put your drawing code here
		update();
		render(drawable);
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		// put your cleanup code here
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		// put your OpenGL initialization code here
		drawable.getGL().setSwapInterval(1);
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		// called when user resizes the window
		
	}
	
	private void update() {
		// nothing to update yet
		theta += 0.01;
		s = Math.sin(theta);
		c = Math.cos(theta);
	}
	
	private void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		// draw a triangle filling the window
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glColor3f(1, 0, 0);
		gl.glVertex2d(-c, -c);
		gl.glColor3f(0, 1, 0);
		gl.glVertex2d(0, c);
		gl.glColor3f(0, 0, 1);
		gl.glVertex2d(s, -s);
		gl.glEnd();
	}

}
