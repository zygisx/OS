package gui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class ButtonsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panelLeft = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelLeft.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panelLeft);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setHorizontalAlignment(SwingConstants.LEFT);
		panelLeft.add(btnExit);
		
		JPanel panelRight = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelRight.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panelRight);
		
		JButton btnLoad = new JButton("Load");
		panelRight.add(btnLoad);
		
		JButton btnStep = new JButton("Step");
		panelRight.add(btnStep);
		
		JButton btnRun = new JButton("Run");
		btnRun.setHorizontalAlignment(SwingConstants.RIGHT);
		panelRight.add(btnRun);

	}

}
