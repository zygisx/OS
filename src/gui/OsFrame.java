package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

public class OsFrame extends JFrame {
	
	private JPanel contentPanel;
	private JTabbedPane tabsPanel;
	private ProcessesPanel processesPanel;
	private ResourcesPanel resourcesPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilitiesPanel utilitiesPanel;
	
	
	/**
	 *  Create frame
	 */

	public OsFrame() {
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
		setBounds(100, 100, 950, 600);
		
		//set content panel
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
		
		//set tabbed panel
		
		tabsPanel = new JTabbedPane();
		tabsPanel.setBackground(Color.WHITE);
		tabsPanel.setBorder(null);
		
		contentPanel.add(tabsPanel);
		
		processesPanel = new ProcessesPanel();
		tabsPanel.addTab("Processes", null, processesPanel, null);
		
		resourcesPanel = new ResourcesPanel();
		tabsPanel.addTab("Resources", null, resourcesPanel, null);
		
		
		
		utilitiesPanel = new UtilitiesPanel();
		contentPanel.add(utilitiesPanel, BorderLayout.SOUTH);
		

	}
	
	public ProcessesPanel getProcessesPanel() {
		return (ProcessesPanel) this.processesPanel;
	}
	
	public void update() {
		this.processesPanel.update();
		this.resourcesPanel.update();
	}
}
