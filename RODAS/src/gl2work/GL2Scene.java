package gl2work;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import vec3D.Vec3D;

public class GL2Scene implements GLEventListener {
	
	private GLU  glu  = new GLU();		// get GL Utilities
	private GLUT glut = new GLUT();		// for drawing the teapot
	
	private static float deltax = 0;
	private static float deltay = 0;
	private static float deltaz = 0;
	private static float scalez = 1;
	
	private static float angle = 0;
	private static Vec3D axis = new Vec3D();
	

	/**
	* Called back by the animator to perform rendering.
	*/
	public void display(GLAutoDrawable drawable) {
		
		GL2 gl = drawable.getGL().getGL2();								// get the OpenGL graphics context
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);	// clear color and depth buffers
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		
		gl.glLoadIdentity();				// reset the model-view matrix
		gl.glOrtho(-1,1,-1,1,-2,2);
		
		if (angle != 0) {
			gl.glRotatef(angle, axis.x, axis.y, axis.z);
		}
		if ((deltax !=0)|(deltay != 0)|(deltaz != 0)) {
			gl.glTranslatef(deltax, deltay, deltaz);
		}
		if (scalez != 1) {
			gl.glScalef(scalez, scalez, scalez);
		}
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);

		gl.glLoadIdentity();             // Set up model view transform.
		
		
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
		
		gl.glColor3f(1, 1, 1);
		glut.glutWireTeapot(0.6);
		
		// axes
		gl.glBegin(GL.GL_LINES);
			gl.glLineWidth(1);
			gl.glColor3f(1, 0, 0);		gl.glVertex3f(0, 0, 0);		gl.glVertex3f(1, 0, 0);
			gl.glColor3f(0, 1, 0);		gl.glVertex3f(0, 0, 0);		gl.glVertex3f(0, 1, 0);
			gl.glColor3f(0, 0, 1);		gl.glVertex3f(0, 0, 0);		gl.glVertex3f(0, 0, 1);
		gl.glEnd();
		
		// axes names
		gl.glColor3f(1, 0, 0);	gl.glRasterPos3f(1, 0, 0);	glut.glutBitmapString(GLUT.BITMAP_9_BY_15, "X");
		gl.glColor3f(0, 1, 0);	gl.glRasterPos3f(0, 1, 0);	glut.glutBitmapString(GLUT.BITMAP_9_BY_15, "Y");
		gl.glColor3f(0, 0, 1);	gl.glRasterPos3f(0, 0, 1);	glut.glutBitmapString(GLUT.BITMAP_9_BY_15, "Z");
		
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
		gl.glOrtho(-1,1,-1,1,-2,2);
		//gl.glViewport(0, 640, 0, 480);
		//glu.gluOrtho2D( 0, 1, 0, 1);
		
		
		glu.gluLookAt(
		          0, 0, 10, 	// eye
		           0, 0, 0, 	// at
		           0, 1, 0 		// up
		           );
		
		// GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble near, GLdouble far
		// GLdouble left, GLdouble right, GLdouble bottom, GLdouble top
		
		
		// Enable the model-view transform
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}
	
	
	
	public static void setTranslate(float deltax, float deltay, float deltaz) {
		GL2Scene.deltax = deltax;
		GL2Scene.deltay = deltay;
		GL2Scene.deltay = deltaz;
	}
	
	public static void setRotate(Vec3D vr, float angle) {
		GL2Scene.axis  = vr;
		GL2Scene.angle = angle;
	}
	
	public static void setScale(float scalez) {
		GL2Scene.scalez = scalez;
	}

}
