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
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;

public class Main {

	private JFrame mainWindow;
	private JTextField textField;
	private Integer xCursorPosition;
	private Integer yCursorPosition;
	private final ButtonGroup lfButtonGroup = new ButtonGroup();

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
		
		JMenuItem menuFileOpen = new JMenuItem("Open...");
		menuFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuFileOpen.addMouseListener(new MouseAdapter() {
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
		
		JMenu menuLF = new JMenu("L&F");
		menuBar.add(menuLF);
		
		final UIManager.LookAndFeelInfo[] lfinfo = UIManager.getInstalledLookAndFeels();
		for (int i = 0; i < lfinfo.length; i++) {
			final LookAndFeelInfo lfName = lfinfo[i];
			JRadioButtonMenuItem lfitem = new JRadioButtonMenuItem(lfinfo[i].getName());
			lfitem.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String lookAndFeel = lfName.getClassName();
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
					};
				}
				
			});
			menuLF.add(lfitem);
			lfButtonGroup.add(lfitem);
		}
		
		UIManager.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				SwingUtilities.updateComponentTreeUI(mainWindow);
			}
		});
		
		// setup System L&F as default
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
	}

}
