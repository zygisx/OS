package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import machine.Realmachine;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel componentsPanel;
	private JPanel buttonsPanel;
	private ConsolePanel console;
	private static boolean isInputAccepted;
	private static String input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Operating System Emulator");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 520);
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
				((ComponentsPanel) MainFrame.this.getComponentsPanel()).update();
				
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
				MainFrame.getInput();
				((ComponentsPanel) MainFrame.this.getComponentsPanel()).update();
				
			}
		});
		rightPanel.add(btnLoad);
		
		JButton btnStep = new JButton("Step");
		rightPanel.add(btnStep);
		
		JButton btnRun = new JButton("Run");
		rightPanel.add(btnRun);
		
		
		// CONSOLE PANEL
		console = new ConsolePanel(this);
		contentPane.add(console, BorderLayout.EAST);
		
		
		
		
		// // // ONLY TEST PURPOSE
		Realmachine.initVirtualMachine("././program.txt");
	}
	
	public static String getInput()  {
		isInputAccepted = false;
		while (! isInputAccepted) {
			
		}
		System.out.println(input);
		return input;
	}
	
	public void output() {
		
	}
	
	public void inputAccepted(String in) {
		isInputAccepted = true;
		input = in;
	}
	
	public JPanel getComponentsPanel() {
		return this.componentsPanel;
	}

}
