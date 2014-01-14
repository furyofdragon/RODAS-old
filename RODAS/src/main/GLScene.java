package main;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class GLScene implements GLEventListener {

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		update();
		render(drawable);
	}

	private void render(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	private void update() {
		// TODO Auto-generated method stub
		
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

}
