package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import os.Kernel;

public class UtilitiesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilitiesPanel() {
		setBorder(null);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(800, 120));
		setLayout(null);
		
		JButton btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kernel.setNextStep(true);
				//System.out.println(Kernel.nextStep);
			}
		});
		btnStep.setBounds(10, 41, 55, 23);
		add(btnStep);
	}
	
}
