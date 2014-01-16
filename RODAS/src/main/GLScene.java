package main;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GLScene implements GLEventListener {
	
	private GLU glu;  // for the GL Utility
	
	private float angle = 1;

	/**
	* Called back by the animator to perform rendering.
	*/
	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		update();
		render(drawable);
	}

	/**
	* Called back before the OpenGL context is destroyed. Release resource such as buffers.
	*/
	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	/**
	* Called back immediately after the OpenGL context is initialized. Can be used
	* to perform one-time initialization. Run only once.
	*/
	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();		// get the OpenGL graphics context
		glu = new GLU();						// get GL Utilities
		gl.setSwapInterval(1);
		//drawable.getGL().setSwapInterval(1);
	}

	/**
	* Call-back handler for window re-size event. Also called when the drawable is
	* first set to visible.
	*/
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();		// get the OpenGL graphics context
		if (height == 0) height = 1;			// prevent divide by zero
		float aspect = (float) width / height;
		
		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);
		
		// Setup perspective projection, with aspect ratio matches viewport
		gl.glLoadIdentity();							// reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); 	// fovy, aspect, zNear, zFar
		
		// Enable the model-view transform
		gl.glLoadIdentity(); // reset
	}
	
	private void update() {
		// TODO Auto-generated method stub
		
		
		angle = angle + 0.1f;
		
	}
	
	private void render(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);	// clear color and depth buffers
		gl.glLoadIdentity();  // reset the model-view matrix
		
		/* colors
		glColor3f(0.0, 0.0, 0.0);           black
		glColor3f(1.0, 0.0, 0.0);           red
		glColor3f(0.0, 1.0, 0.0);           green
		glColor3f(1.0, 1.0, 0.0);           yellow
		glColor3f(0.0, 0.0, 1.0);           blue
		glColor3f(1.0, 0.0, 1.0);           magenta
		glColor3f(0.0, 1.0, 1.0);           cyan
		glColor3f(1.0, 1.0, 1.0);           white
		 * 
		 */
		
		
		// ----- OpenGL rendering code here
		
		gl.glRotatef(angle, 1, 1, 1);
		
		gl.glBegin(GL.GL_TRIANGLES);
			gl.glColor3f(1, 0, 0);		gl.glVertex2d(-1, -1);
			gl.glColor3f(0, 1, 0);		gl.glVertex2d(1, -1);
			gl.glColor3f(0, 0, 1);		gl.glVertex2d(0, 1);
		gl.glEnd();
		
		
		
		/*
		for (int i = 1; ; i++) {
			ReadSource.getvlines();
		}
				
		
		gl.glBegin(GL.GL_LINES);
			gl.glColor3f(1, 1, 1);		gl.glVertex3f(arg0, arg1, arg2);
			gl.glColor3f(1, 1, 1);		gl.glVertex3f(arg0, arg1, arg2);
		gl.glEnd();
		*/
		
	}

}
