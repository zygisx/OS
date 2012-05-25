package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class OsFrame extends JFrame {
	
	private JPanel contentPanel;
	private JTabbedPane tabsPanel;
	private JPanel readyProcessesPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		setBounds(100, 100, 950, 520);
		
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
		
		readyProcessesPanel = new ReadyProcessesPanel();
		tabsPanel.addTab("ReadyProcesses", null, readyProcessesPanel, null);
		

	}
}
