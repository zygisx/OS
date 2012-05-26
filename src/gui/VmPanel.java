package gui;

import javax.swing.JPanel;

public class VmPanel extends JPanel {
	
	private int id;

	VmPanel(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
