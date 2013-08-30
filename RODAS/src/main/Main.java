package main;

import java.awt.EventQueue;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MouseInfo;

import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.JMenuItem;

import com.jogamp.opengl.util.Animator;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Main {

	private JFrame mainWindow;
	private JTextField textField;
	private Integer xCursorPosition;
	private Integer yCursorPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainWindow = new JFrame();
		mainWindow.setTitle("RODAS");
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWindow.setMinimumSize(new Dimension(640, 480));
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		mainWindow.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem menufileOpen = new JMenuItem("Open...");
		menuFile.add(menufileOpen);
		
		JMenuItem menuFileSave = new JMenuItem("Save");
		menuFile.add(menuFileSave);
		
		JMenuItem menuFileSaveAs = new JMenuItem("Save as...");
		menuFile.add(menuFileSaveAs);
		
		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		JMenuItem menuHelpAbout = new JMenuItem("About");
		menuHelp.add(menuHelpAbout);
		
		JPanel rightPanel = new JPanel();
		mainWindow.getContentPane().add(rightPanel, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("New button");
		rightPanel.add(btnNewButton);
		
		// Initialization OpenGl
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLJPanel canvas = new GLJPanel(caps);
		// end of initialization OpenGL
		
		mainWindow.getContentPane().add(canvas, BorderLayout.CENTER);
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				xCursorPosition = MouseInfo.getPointerInfo().getLocation().x;
				yCursorPosition = MouseInfo.getPointerInfo().getLocation().y;
				textField.setText("Cursor position: x = " + Integer.toString(xCursorPosition) + " ; y = " + Integer.toString(yCursorPosition));
			}
		});
		canvas.addGLEventListener(new SimpleScene());
		
		Animator animator = new Animator(canvas);
		//canvas.setAnimator(animator);
		animator.start();
		
		JPanel bottomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) bottomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainWindow.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		textField = new JTextField();
		textField.setEditable(false);
		bottomPanel.add(textField);
		textField.setColumns(20);
		
		JSeparator separator = new JSeparator();
		bottomPanel.add(separator);
	}

}
