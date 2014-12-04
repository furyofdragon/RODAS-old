package main;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

import vec3D.Vec3D;

import com.jogamp.opengl.util.gl2.GLUT;  // for drawing the sample teapot

/**
 * A template for a basic JOGL application with support for animation and for
 * keyboard and mouse event handling.  To enable the support, uncomment the
 * appropriate lines in the init() method.  As an example, the program draws
 * the GLUT teapot.
 */
@SuppressWarnings("serial")
public class JoglTemplate extends JPanel implements 
                   GLEventListener, KeyListener, MouseListener, MouseMotionListener, ActionListener {

	public static void main(String[] args) {
		JFrame window = new JFrame("JOGL");
		window.setContentPane(new JoglTemplate());
		window.pack();
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	private GLJPanel display;
    private Timer    animationTimer;
	private float    rotateX, rotateY;   // rotation amounts about axes, controlled by keyboard
	private float    rotateXprev, rotateYprev;
	
	JLabel lblNewLabel;
	

	public JoglTemplate() {
		GLCapabilities caps = new GLCapabilities(null);
		display = new GLJPanel(caps);
		display.setPreferredSize(new Dimension(600,600));  // TODO: set display size here
		display.addGLEventListener(this);
		setLayout(new BorderLayout());
		add(display,BorderLayout.CENTER);
		// TODO:  Other components could be added to the main panel.
		
		rotateX = 15;  // initialize some variables used in the drawing.
		rotateY = 15;
		
		// TODO:  Uncomment the next two lines to enable keyboard event handling
		display.requestFocusInWindow();
		display.addKeyListener(this);

		// TODO:  Uncomment the next one or two lines to enable mouse event handling
		display.addMouseListener(this);
		display.addMouseMotionListener(this);
		
		lblNewLabel = new JLabel(" ");
		add(lblNewLabel, BorderLayout.SOUTH);
		
		//TODO:  Uncomment the following line to start the animation
		//startAnimation();

	}

	// ---------------  Methods of the GLEventListener interface -----------
	
	private GLUT glut = new GLUT();  // for drawing the teapot
	private GLU  glu  = new GLU();

	/**
	 * This method is called when the OpenGL display needs to be redrawn.
	 */
	public void display(GLAutoDrawable drawable) {	
		    // called when the panel needs to be drawn

		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0,0,0,0);
		gl.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );

		gl.glMatrixMode(GL2.GL_PROJECTION);  // TODO: Set up a better projection?
		gl.glLoadIdentity();
		gl.glOrtho(-1,1,-1,1,-2,2);
		
		if (angle != 0) {
			gl.glRotatef(angle, axis.x, axis.y, axis.z);
		}
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);

		gl.glLoadIdentity();             // Set up model view transform.
		//float r = 1;
		//float a = (float) (r*Math.sin(rotateY*Math.PI/180));
		//float c = (float) (r*Math.cos(rotateY*Math.PI/180));
		//glu.gluLookAt(a, 0, c, 0, 0, 0, 0, 1, 0);
		//gl.glRotatef(rotateY,0,1,0);
		//gl.glRotatef(rotateX,1,0,0);

		// TODO: add drawing code!!  As an example, draw a GLUT teapot
		//glut.glutSolidTeapot(0.5);
		gl.glColor3f(1, 1, 1);
		glut.glutWireTeapot(0.5);
		
		// axes
		gl.glBegin(GL.GL_LINES);
			gl.glLineWidth(1);
			gl.glColor3f(1, 0, 0);		gl.glVertex3f(0, 0, 0);		gl.glVertex3f(1, 0, 0);
			gl.glColor3f(0, 1, 0);		gl.glVertex3f(0, 0, 0);		gl.glVertex3f(0, 1, 0);
			gl.glColor3f(0, 0, 1);		gl.glVertex3f(0, 0, 0);		gl.glVertex3f(0, 0, 1);
		gl.glEnd();
		
		// axes names
		gl.glColor3f(1, 0, 0);
		gl.glRasterPos3f(1, 0, 0);
		glut.glutBitmapString(GLUT.BITMAP_9_BY_15, "X");
		gl.glColor3f(0, 1, 0);
		gl.glRasterPos3f(0, 1, 0);
		glut.glutBitmapString(GLUT.BITMAP_9_BY_15, "Y");
		gl.glColor3f(0, 0, 1);
		gl.glRasterPos3f(0, 0, 1);
		glut.glutBitmapString(GLUT.BITMAP_9_BY_15, "Z");

	}

	/**
	 * This is called when the GLJPanel is first created.  It can be used to initialize
	 * the OpenGL drawing context.
	 */
	public void init(GLAutoDrawable drawable) {
		    // called when the panel is created
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.8F, 0.8F, 0.8F, 1.0F);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
	}

	/**
	 * Called when the size of the GLJPanel changes.  Note:  glViewport(x,y,width,height)
	 * has already been called before this method is called!
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	}

	/**
	 * This is called before the GLJPanel is destroyed.  It can be used to release OpenGL resources.
	 */
	public void dispose(GLAutoDrawable drawable) {
	}

	
	// ------------ Support for keyboard handling  ------------

	/**
	 * Called when the user presses any key.
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();  // Tells which key was pressed.
		if ( key == KeyEvent.VK_LEFT )
			rotateY -= 15;
		else if ( key == KeyEvent.VK_RIGHT )
			rotateY += 15;
		else if ( key == KeyEvent.VK_DOWN)
			rotateX += 15;
		else if ( key == KeyEvent.VK_UP )
			rotateX -= 15;
		else if ( key == KeyEvent.VK_HOME )
			rotateX = rotateY = 0;
		display.repaint();
	}

	/**
	 * Called when the user types a character.
	 */
	public void keyTyped(KeyEvent e) { 
		char ch = e.getKeyChar();  // Which character was typed.
	}

	public void keyReleased(KeyEvent e) { 
	}
	
	// --------------------------- animation support ---------------------------
	
	private int frameNumber = 0;
	
	private boolean animating;
	
	private void updateFrame() {
		// TODO:  add any other updating required for the next frame.
		frameNumber++;
	}
	
	public void startAnimation() {
		if ( ! animating ) {
			if (animationTimer == null) {
				animationTimer = new Timer(30, this);
			}
			animationTimer.start();
			animating = true;
		}
	}
	
	public void pauseAnimation() {
		if (animating) {
			animationTimer.stop();
			animating = false;
		}
	}
	
	public void actionPerformed(ActionEvent evt) {
		updateFrame();
		display.repaint();
	}

	
	
	// ---------------------- support for mouse events ----------------------
	
	private boolean dragging;		// is a drag operation in progress?
	
	private int startX, startY;		// starting location of mouse during drag
	private int prevX , prevY;		// previous location of mouse during drag
	
	private Vec3D va, vb;			// for arcball rotation
	private float angle;
	private Vec3D axis;
	
	/**
	 * Called when the user presses a mouse button on the display.
	 */
	public void mousePressed(MouseEvent evt) {
		if (dragging) {
			return;  // don't start a new drag while one is already in progress
		}
		int x = evt.getX();
		int y = evt.getY();
		// TODO: respond to mouse click at (x,y)
		dragging = true;  // might not always be correct!
		
		// Convert the screen coordinates (in pixels) to camera coordinates (in [-1, 1])
		// and reverse y coordinates
		float ax = 2* (float)x/ (float)display.getWidth () - 1f;
		float ay = 2* (float)y/ (float)display.getHeight() - 1f;
		ay = - ay;
		va = new Vec3D(ax, ay);
		float squared = ax*ax + ay*ay;
		if (squared <= 1) {
			va.z = (float) Math.sqrt(1f - squared);	// Pythagorene
		}else {
			va = Vec3D.normalize(va);				// nearest point
		}
		
		//startX = x;
		//startY = y;
		//prevX = startX = x;
		//prevY = startY = y;
		//rotateXprev = rotateX;
		//rotateYprev = rotateY;
		display.repaint();    //  only needed if display should change
	}

	/**
	 * Called when the user releases a mouse button after pressing it on the display.
	 */
	public void mouseReleased(MouseEvent evt) {
		if (! dragging) {
			return;
		}
		dragging = false;
		// TODO:  finish drag (generally nothing to do here)
	}

	/**
	 * Called during a drag operation when the user drags the mouse on the display/
	 */
	public void mouseDragged(MouseEvent evt) {
		if (! dragging) {
			return;
		}
		int x = evt.getX();
		int y = evt.getY();
		
		// Convert the screen coordinates (in pixels) to camera coordinates (in [-1, 1])
		// and reverse y coordinates
		float ax = 2* (float)x/ (float)display.getWidth () - 1f;
		float ay = 2* (float)y/ (float)display.getHeight() - 1f;
		ay = - ay;
		vb = new Vec3D(ax, ay);
		float squared = ax*ax + ay*ay;
		if (squared <= 1) {
			vb.z = (float) Math.sqrt(1f - squared);	// Pythagorene
		}else {
			vb = Vec3D.normalize(vb);				// nearest point
		}
		
		// Compute the angle and axis
		angle = (float) (180f/Math.PI * Math.acos(Math.min(1f, Vec3D.scalarProduce(va, vb))));
		axis  = Vec3D.vectorProduce(va, vb);
		
		lblNewLabel.setText("angle = "     + Float.toString(angle) +
							"    vbx = "   + Float.toString(vb.x)  +
							"    vby = "   + Float.toString(vb.y)  +
							"    vbz = "   + Float.toString(vb.z)  +
							"    ax  = "   + Float.toString(ax)    +
							"    ay  = "   + Float.toString(ay)    +
							"    SP  = "   + Float.toString(Vec3D.scalarProduce(va, vb)));
		
		// TODO:  respond to mouse drag to new point (x,y)
		//prevX = x;
		//prevY = y;
		//rotateX += ( startY - prevY)*0.01f;
		//rotateY += (-startX + prevX)*0.01f;
		display.repaint();
	}

	public void mouseMoved  (MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited (MouseEvent evt) { }

}
