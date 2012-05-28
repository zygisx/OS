package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import os.Kernel;
import javax.swing.JCheckBox;

public class UtilitiesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox chckbxBigStep;
	private JButton btnStep;

	public UtilitiesPanel() {
		setBorder(null);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(800, 120));
		setLayout(null);
		
		chckbxBigStep = new JCheckBox("Big step");
		chckbxBigStep.setMnemonic(KeyEvent.VK_B);
		chckbxBigStep.setBackground(Color.WHITE);
		chckbxBigStep.setBounds(71, 41, 63, 23);
		add(chckbxBigStep);
		
		btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kernel.setBigStep(chckbxBigStep.isSelected());
				Kernel.setNextStep(true);
				//System.out.println(Kernel.nextStep);
			}
		});
		btnStep.setBounds(10, 41, 55, 23);
		add(btnStep);
	
	}
	
	public void turnOnButtons() {
		btnStep.setEnabled(true);
		chckbxBigStep.setEnabled(true);
	}
	
	public void turnOffButtons() {
		btnStep.setEnabled(false);
		chckbxBigStep.setEnabled(false);
	}
}
