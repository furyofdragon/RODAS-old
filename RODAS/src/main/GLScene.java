package main;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GLScene implements GLEventListener {
	
	private GLU glu;  // for the GL Utility
	
	private static float deltax;
	private static float deltay;
	private static float rotx;
	private static float roty;

	/**
	* Called back by the animator to perform rendering.
	*/
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();								// get the OpenGL graphics context
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);	// clear color and depth buffers
		gl.glLoadIdentity();											// reset the model-view matrix
		
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
		
		//gl.glRotatef(angle, 1, 1, 1);
		//gl.glTranslatef(deltax, deltay, 0);
		//gl.glRotatef(rotx, 1, 0, 0);
		//gl.glRotatef(roty, 0, 1, 0);
		
		//the gluLookAt() method, which takes three 3D coordinates:
		//(0, 0, distance): Where we are standing ("eye")
		//(0, 0, 0): Where we are looking at ("at"): Directly at the center of the coordinate system.
		//(0, 1, 0): Where our head points into the sky ("up"): Directly up, along the Y coordinate
		//(remember that Y is counted as if on a graph, not like in an image manipulation program).
		
		gl.glLoadIdentity();  // reset the model-view matrix
		gl.glRotatef(rotx, 1, 0, 0);
		gl.glRotatef(roty, 0, 1, 0);
		gl.glTranslatef(deltax, deltay, 0);
		
		gl.glBegin(GL.GL_TRIANGLES);
			gl.glColor3f(1, 0, 0);		gl.glVertex2d(-1, -1);
			gl.glColor3f(0, 1, 0);		gl.glVertex2d(1, -1);
			gl.glColor3f(0, 0, 1);		gl.glVertex2d(0, 1);
		gl.glEnd();
		
		
		
	}

	/**
	* Called back before the OpenGL context is destroyed. Release resource such as buffers.
	*/
	public void dispose(GLAutoDrawable drawable) {
		
	}

	/**
	* Called back immediately after the OpenGL context is initialized. Can be used
	* to perform one-time initialization. Run only once.
	*/
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();		// get the OpenGL graphics context
		glu = new GLU();						// get GL Utilities
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);// set background (clear) color -- black
		gl.glClearDepth(1.0f);					// set clear depth value to farthest
		gl.glEnable(GL2.GL_DEPTH_TEST);			// enables depth testing
		gl.glDepthFunc(GL2.GL_LEQUAL);			// the type of depth test to do
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
		gl.glShadeModel(GL2.GL_SMOOTH);			// blends colors nicely, and smoothes out lighting
		gl.setSwapInterval(1);
	}

	/**
	* Call-back handler for window re-size event. Also called when the drawable is
	* first set to visible.
	*/
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();		// get the OpenGL graphics context
		if (height == 0) height = 1;			// prevent divide by zero
		float aspect = (float) width / height;
		
		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);
		
		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL2.GL_PROJECTION);				// choose projection matrix
		gl.glLoadIdentity();							// reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); 	// fovy, aspect, zNear, zFar
		
		// Enable the model-view transform
		gl.glLoadIdentity(); // reset
	}
	
	
	
	static void setTranslate(float deltax, float deltay) {
		GLScene.deltax = deltax;
		GLScene.deltay = deltay;
	}
	
	static void setRotate(float rotx, float roty) {
		GLScene.rotx = rotx;
		GLScene.roty = roty;
	}

}
