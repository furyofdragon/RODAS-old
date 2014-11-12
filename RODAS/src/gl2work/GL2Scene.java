package gl2work;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

public class GL2Scene implements GLEventListener {
	
	private GLU glu;  // for the GL Utility
	
	private static float deltax;
	private static float deltay;
	private static float deltaz;
	private static float rotx;
	private static float roty;
	
	
	   private float anglePyramid = 0;    // rotational angle in degree for pyramid
	   private float angleCube = 0;       // rotational angle in degree for cube
	   private float speedPyramid = 2.0f; // rotational speed for pyramid
	   private float speedCube = 1.5f;   // rotational speed for cube

	/**
	* Called back by the animator to perform rendering.
	*/
	public void display(GLAutoDrawable drawable) {
		render(drawable);
		update();
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
		GL2 gl = drawable.getGL().getGL2();								// get the OpenGL graphics context
		glu = new GLU();												// get GL Utilities
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);						// set background (clear) color -- black
		gl.glClearDepth(1.0f);											// set clear depth value to farthest
		gl.glEnable(GL2.GL_DEPTH_TEST);									// enables depth testing
		gl.glDepthFunc(GL2.GL_LEQUAL);									// the type of depth test to do
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);	// best perspective correction
		gl.glShadeModel(GL2.GL_SMOOTH);									// blends colors nicely, and smoothes out lighting
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);						// Set background color in RGBA. Alpha: 0 (transparent) 1 (opaque)
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);	// Do the best perspective correction
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
		//glu.gluPerspective(45.0, aspect, 0.01, 100.0); 	// fovy, aspect, zNear, zFar
		
		glu.gluOrtho2D( 0, 10, 0, 10);
		
		//glu.gluLookAt(
		//           0, 0, 10, 	// eye
		//           0, 0, 0, 	// at
		//           0, 1, 0 		// up
		//           );
		
		// GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble near, GLdouble far
		// GLdouble left, GLdouble right, GLdouble bottom, GLdouble top
		
		
		// Enable the model-view transform
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}
	
	
	
	private void render(GLAutoDrawable drawable) {
		
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
		
		
		/*
		 * coordinate system
		 * X - from left to right
		 * Y - from bottom to up
		 * Z - from screen to us
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
		
		
		// ----- Render my triangle -----
		gl.glLoadIdentity();                 // reset the model-view matrix
		gl.glTranslatef(0.5f, 0.0f, -6.0f);
		gl.glTranslatef(deltax, deltay, 0f);
		gl.glRotatef(rotx, 1, 0, 0);
		gl.glRotatef(roty, 0, 1, 0);
		if (deltaz == 0f) deltaz = 1f;
		gl.glScalef(deltaz, deltaz, deltaz);
				
		gl.glBegin(GL.GL_TRIANGLES);
			gl.glColor3f(1, 0, 0);		gl.glVertex3d(-1, 0, 0);
			gl.glColor3f(0, 1, 0);		gl.glVertex3d( 1, 0, 0);
			gl.glColor3f(0, 0, 1);		gl.glVertex3d( 0, 1, 0);
		gl.glEnd();
		
		
	      // ----- Render the Pyramid -----
	      gl.glLoadIdentity();                 // reset the model-view matrix
	      gl.glTranslatef(-1.6f, 0.0f, -6.0f); // translate left and into the screen
	      gl.glRotatef(anglePyramid, -0.2f, 1.0f, 0.0f); // rotate about the y-axis
	 
	      gl.glBegin(GL.GL_TRIANGLES); // of the pyramid
	 
	      // Font-face triangle
	      gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
	      gl.glVertex3f(0.0f, 1.0f, 0.0f);
	      gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
	      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	      gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
	      gl.glVertex3f(1.0f, -1.0f, 1.0f);
	 
	      // Right-face triangle
	      gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
	      gl.glVertex3f(0.0f, 1.0f, 0.0f);
	      gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
	      gl.glVertex3f(1.0f, -1.0f, 1.0f);
	      gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
	      gl.glVertex3f(1.0f, -1.0f, -1.0f);
	 
	      // Back-face triangle
	      gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
	      gl.glVertex3f(0.0f, 1.0f, 0.0f);
	      gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
	      gl.glVertex3f(1.0f, -1.0f, -1.0f);
	      gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
	      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	 
	      // Left-face triangle
	      gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
	      gl.glVertex3f(0.0f, 1.0f, 0.0f);
	      gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
	      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	      gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
	      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	 
	      gl.glEnd(); // of the pyramid
	 
	      // ----- Render the Color Cube -----
	      gl.glLoadIdentity();                // reset the current model-view matrix
	      gl.glTranslatef(1.6f, 0.0f, -7.0f); // translate right and into the screen
	      //gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // rotate about the x, y and z-axes
	      gl.glRotatef(angleCube, 0f, 0f, 1.0f);
	 
	      gl.glBegin(GL2.GL_QUADS); // of the color cube
	 
	      // Top-face
	      gl.glColor3f(0.0f, 1.0f, 0.0f); // green
	      gl.glVertex3f(1.0f, 1.0f, -1.0f);
	      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
	      gl.glVertex3f(-1.0f, 1.0f, 1.0f);
	      gl.glVertex3f(1.0f, 1.0f, 1.0f);
	 
	      // Bottom-face
	      gl.glColor3f(1.0f, 0.5f, 0.0f); // orange
	      gl.glVertex3f(1.0f, -1.0f, 1.0f);
	      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	      gl.glVertex3f(1.0f, -1.0f, -1.0f);
	 
	      // Front-face
	      gl.glColor3f(1.0f, 0.0f, 0.0f); // red
	      gl.glVertex3f(1.0f, 1.0f, 1.0f);
	      gl.glVertex3f(-1.0f, 1.0f, 1.0f);
	      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	      gl.glVertex3f(1.0f, -1.0f, 1.0f);
	 
	      // Back-face
	      gl.glColor3f(1.0f, 1.0f, 0.0f); // yellow
	      gl.glVertex3f(1.0f, -1.0f, -1.0f);
	      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
	      gl.glVertex3f(1.0f, 1.0f, -1.0f);
	 
	      // Left-face
	      gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
	      gl.glVertex3f(-1.0f, 1.0f, 1.0f);
	      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
	      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	 
	      // Right-face
	      gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
	      gl.glVertex3f(1.0f, 1.0f, -1.0f);
	      gl.glVertex3f(1.0f, 1.0f, 1.0f);
	      gl.glVertex3f(1.0f, -1.0f, 1.0f);
	      gl.glVertex3f(1.0f, -1.0f, -1.0f);
	 
	      gl.glEnd(); // of the color cube
		
		
	}
	
	
	
	private void update() {
	      anglePyramid += speedPyramid;
	      angleCube += speedCube;
	}
	
	
	public static void setTranslate(float deltax, float deltay, float deltaz) {
		GL2Scene.deltax = deltax;
		GL2Scene.deltay = deltay;
		GL2Scene.deltay = deltaz;
	}
	
	public static void setRotate(float rotx, float roty) {
		GL2Scene.rotx = rotx;
		GL2Scene.roty = roty;
	}
	
	public static void setScale(float deltaz) {
		GL2Scene.deltaz = deltaz;
	}

}
