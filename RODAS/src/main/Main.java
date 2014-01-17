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

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.FlowLayout;

import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jogamp.opengl.util.Animator;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame mainWindow;
	private JTextField textField;
	private Integer xCursorPosition;
	private Integer yCursorPosition;
	
	// Specify the look and feel to use by defining the LOOKANDFEEL constant
	// Valid values are: null (use the default), "Metal", "System", "Motif", and "GTK"
	static String lookAndFeel = "System";

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
		//Set the look and feel
		initLookAndFeel();
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
		
		JMenuItem menuFileOpen = new JMenuItem("Open...");
		menuFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuFileOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				JFileChooser fileopen = new JFileChooser();
				//fileopen.addChoosableFileFilter(new FileNameExtensionFilter("RODAS model (*.rfm)", "rfm"));
				fileopen.setFileFilter(new FileNameExtensionFilter("RODAS model (*.rm)", "rm"));
				//fileopen.showDialog(null, "Open file");
				int rOpen = fileopen.showOpenDialog(mainWindow);
				switch (rOpen) {
					case JFileChooser.APPROVE_OPTION :
						String inFile = fileopen.getSelectedFile().getPath();
						ReadSource.ReadDataSource(inFile);
						break;
					case JFileChooser.CANCEL_OPTION :
						break;
					case JFileChooser.ERROR_OPTION :
						break;
				}
			}
		});
		menuFile.add(menuFileOpen);
		
		JMenuItem menuFileSave = new JMenuItem("Save");
		menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuFile.add(menuFileSave);
		
		JMenuItem menuFileSaveAs = new JMenuItem("Save as...");
		menuFile.add(menuFileSaveAs);
		
		JMenu menuLf = new JMenu("L&F");
		menuBar.add(menuLf);
		
		JMenuItem menuLFSystem = new JMenuItem("System");
		menuLFSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lookAndFeel = "System";
			}
		});
		menuLf.add(menuLFSystem);
		
		JMenuItem menuLFMetal = new JMenuItem("Metal");
		menuLFMetal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lookAndFeel = "Metal";
			}
		});
		menuLf.add(menuLFMetal);
		
		JMenuItem menuLFMotif = new JMenuItem("Motif");
		menuLFMotif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lookAndFeel = "Motif";
			}
		});
		menuLf.add(menuLFMotif);
		
		JMenuItem menuLFGtk = new JMenuItem("GTK");
		menuLFGtk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lookAndFeel = "GTK";
			}
		});
		menuLf.add(menuLFGtk);
		
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
		canvas.addGLEventListener(new GLScene());
		
		final Animator animator = new Animator(canvas);
		animator.start();
		
		JPanel bottomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) bottomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainWindow.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(20);
		bottomPanel.add(textField);
		
		JSeparator separator = new JSeparator();
		bottomPanel.add(separator);
		
		mainWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// Use a dedicate thread to run the stop() to ensure that the
				// animator stops before program exits.
				new Thread() {
					@Override
					public void run() {
						if (animator.isStarted()) animator.stop();
						System.exit(0);
					}
				}.start();
			}
		});
		
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(mainWindow);
		mainWindow.pack();
	}
	
	
	
	private static void initLookAndFeel() {
		//String THEME = "Ocean";
		if (lookAndFeel != null) {
			if (lookAndFeel.equals("Metal")) {
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
				// an alternative way to set the Metal L&F is to replace the previous line with:
				// lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
			}
			
			else if (lookAndFeel.equals("System")) {
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			} 
			
			else if (lookAndFeel.equals("Motif")) {
				lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			}
			
			else if (lookAndFeel.equals("GTK")) {
				lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			} 
			
			else {
				System.err.println("Unexpected value of LOOKANDFEEL specified: " + lookAndFeel);
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			}
		}
		
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		}
		catch (ClassNotFoundException e) {
			System.err.println("Couldn't find class for specified look and feel:" + lookAndFeel);
			System.err.println("Did you include the L&F library in the class path?");
			System.err.println("Using the default look and feel.");
		}
		catch (UnsupportedLookAndFeelException e) {
			System.err.println("Can't use the specified look and feel (" + lookAndFeel + ") on this platform.");
			System.err.println("Using the default look and feel.");
		}
		catch (Exception e) {
			System.err.println("Couldn't get specified look and feel (" + lookAndFeel + "), for some reason.");
			System.err.println("Using the default look and feel.");
			e.printStackTrace();
		}
	}

}
