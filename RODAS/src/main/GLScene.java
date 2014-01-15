package main;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class GLScene implements GLEventListener {
	
	private float angle = 1;

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		update();
		render(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		drawable.getGL().setSwapInterval(1);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	private void update() {
		// TODO Auto-generated method stub
		
		
		//angle = angle + 0.01f;
		
	}
	
	private void render(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
		
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		gl.glRotatef(angle, 1, 1, 1);
		
		gl.glBegin(GL.GL_TRIANGLES);
			gl.glColor3f(1, 0, 0);		gl.glVertex2d(0, 0);
			gl.glColor3f(0, 1, 0);		gl.glVertex2d(0, 1);
			gl.glColor3f(0, 0, 1);		gl.glVertex2d(1, 1);
		gl.glEnd();
		
		
		
	}

}
