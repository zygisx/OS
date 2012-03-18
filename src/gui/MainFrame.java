package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import exception.BadFileException;

import machine.Realmachine;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel componentsPanel;
	private JPanel buttonsPanel;
	private ConsolePanel console;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		//set windows look instead of default swing
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
				    "Unexpected error:\n" + e.getMessage(),
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("Operating System Emulator");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		/* Components panel */
		componentsPanel = new ComponentsPanel();
		contentPane.add(componentsPanel, BorderLayout.WEST);
		
		
		/* Buttons panel */
		buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) leftPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		buttonsPanel.add(leftPanel);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		leftPanel.add(btnExit);
		
		JPanel rightPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) rightPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		buttonsPanel.add(rightPanel);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("././");
				int returnVal = fc.showOpenDialog(MainFrame.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            try {
		            	Realmachine.initVirtualMachine(file.getName());
		            } catch (BadFileException ex) {
		            	JOptionPane.showMessageDialog(MainFrame.this,
							    ex.getMessage(),
							    "Bad file exception",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}    
		        }
		        MainFrame.this.update();
				//FIXME For testing input !
//				MainFrame.this.input();
//				((ComponentsPanel) MainFrame.this.getComponentsPanel()).update();
				
			}
		});
		rightPanel.add(btnLoad);
		
		JButton btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Realmachine.getActiveVM() == null || Realmachine.getActiveVM().isHalted()) {
					JOptionPane.showMessageDialog(MainFrame.this,
						    "Not virtual machine loaded.",
						    "VM error",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				Realmachine.getActiveVM().step();
				MainFrame.this.update();
				
			}
		});
		rightPanel.add(btnStep);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Realmachine.getActiveVM() == null || Realmachine.getActiveVM().isHalted()) {
					JOptionPane.showMessageDialog(MainFrame.this,
						    "No virtual machine loaded.",
						    "VM error",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				Realmachine.getActiveVM().run();
				MainFrame.this.update();
				
			}
		});
		rightPanel.add(btnRun);
		
		
		// CONSOLE PANEL
		console = new ConsolePanel();
		contentPane.add(console, BorderLayout.EAST);
		
	
	}
	
	public String input()  {
		
		String s = (String)JOptionPane.showInputDialog(
				this, "Enter your input", "Input", JOptionPane.OK_OPTION);
		while (s == null || s.equals("")) {
			
			if (s == null || s.equals("")) {
				JOptionPane.showMessageDialog(this,
					    "No valid input.",
					    "Input error",
					    JOptionPane.ERROR_MESSAGE);
			}
			s = (String)JOptionPane.showInputDialog(
					this, "Enter your input", "Input", JOptionPane.OK_OPTION);
		}
		return s;
	}
	
	public void output(String text) {
		this.console.output(text);
	}
	
	public JPanel getComponentsPanel() {
		return this.componentsPanel;
	}
	
	public void update() {
		((ComponentsPanel) MainFrame.this.getComponentsPanel()).update();
	}

}
