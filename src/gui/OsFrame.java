package gui;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

import os.Kernel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;

import exception.VirtualMachineProgramException;

public class OsFrame extends JFrame {
	
	private JPanel contentPanel;
	private JTabbedPane tabsPanel;
	private ProcessesPanel processesPanel;
	private ResourcesPanel resourcesPanel;
	private RealMachinePanel realMachinePanel;
	private JLabel activeVmLabel;
	private JButton btnLoadTask, btnTurnOn, btnShutDown;
	
	private ArrayList<VmPanel> vmPanelsList = new ArrayList<VmPanel>();

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
		
		realMachinePanel = new RealMachinePanel();
		tabsPanel.addTab("Real Machine", null, realMachinePanel, null);
		
		
		
		utilitiesPanel = new UtilitiesPanel();
		contentPanel.add(utilitiesPanel, BorderLayout.SOUTH);
		
		btnLoadTask = new JButton("Load task");
		btnLoadTask.setEnabled(false);
		btnLoadTask.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser("././");
				int returnVal = fc.showOpenDialog(OsFrame.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            Kernel.addTaskDynamically(file.getName());
		        }
			}
		});
		
		btnLoadTask.setBounds(10, 73, 89, 23);
		utilitiesPanel.add(btnLoadTask);	
		
		JLabel lblNewLabel = new JLabel("Active VM:");
		lblNewLabel.setBounds(125, 74, 54, 23);
		utilitiesPanel.add(lblNewLabel);
		
		activeVmLabel = new JLabel("");
		activeVmLabel.setBounds(189, 74, 100, 23);
		utilitiesPanel.add(activeVmLabel);
		
		utilitiesPanel.turnOffButtons();
		
		btnShutDown = new JButton("Shut down");
		setShutDownButton(false);
		btnShutDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kernel.turnOffSystem();
//				btnTurnOn.setEnabled(true);
				btnShutDown.setEnabled(false);
				btnLoadTask.setEnabled(false);
				//utilitiesPanel.turnOffButtons();
				
			}
		});
		btnShutDown.setBounds(825, 73, 89, 23);
		utilitiesPanel.add(btnShutDown);
		
		btnTurnOn = new JButton("Turn on");
		btnTurnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kernel.setIsSystemOn(true);
				setTurnOnButton(false);
				setShutDownButton(true);
				btnLoadTask.setEnabled(true);
				turnOnUtillsButtons();
			}
		});
		btnTurnOn.setBounds(726, 73, 89, 23);
		utilitiesPanel.add(btnTurnOn);
	}
	
	public void turnOnUtillsButtons() {
		this.utilitiesPanel.turnOnButtons();
	}
	
	public void turnOffUtillsButtons() {
		this.utilitiesPanel.turnOffButtons();
	}
	
	public void setActiveVmLabel(int num) {
		activeVmLabel.setText(Integer.toString(num));
	}
	
	public ProcessesPanel getProcessesPanel() {
		return (ProcessesPanel) this.processesPanel;
	}
	
	public void update() {
		this.processesPanel.update();
		this.resourcesPanel.update();
		this.realMachinePanel.update();
		
		for (VmPanel vmPanel : vmPanelsList) {
			vmPanel.update();
		}
	}
	
	public void addVmTab(int id) {
		VmPanel vmPanel = new VmPanel(id);
		vmPanelsList.add(vmPanel);
		
		tabsPanel.addTab("VM " + id, null, vmPanel, null);
	}
	
	public void removeVmTab(int id) {
		
		VmPanel vmPanelToRemove = null;
		
		for (VmPanel vmPanel : vmPanelsList) {
			if (vmPanel.getId() == id) {
				vmPanelToRemove = vmPanel;
			}
		}
		
		tabsPanel.remove(vmPanelToRemove);
		vmPanelsList.remove(vmPanelToRemove);
	}
	
	public VmPanel getVmTab(int id) {
		for (VmPanel vmPanel : vmPanelsList) {
			if (vmPanel.getId() == id) {
				return vmPanel;
			}
		}
		return null;
	}
	
	public void output(String output, int id) {
		for (VmPanel vmPanel : vmPanelsList) {
			if (vmPanel.getId() == id) {
				vmPanel.output(output);
			}
		}
	}
	
	public String input(int num)  {
		
		String s = (String)JOptionPane.showInputDialog(
				this, "Enter your input", "Input (VM " + num +")", JOptionPane.INFORMATION_MESSAGE);
		while (s == null || s.equals("")) {
			
			if (s == null || s.equals("")) {
				JOptionPane.showMessageDialog(this,
					    "Not valid input.",
					    "Input error",
					    JOptionPane.ERROR_MESSAGE);
			}
			s = (String)JOptionPane.showInputDialog(
					this, "Enter your input", "Input (VM " + num +")", JOptionPane.INFORMATION_MESSAGE);
		}
		return s;
	}
	
	public void setTurnOnButton(boolean value) {
		btnTurnOn.setEnabled(value);
	}
	
	public void setShutDownButton(boolean value) {
		btnShutDown.setEnabled(value);
	}
}
