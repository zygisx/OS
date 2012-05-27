package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class ConsolePanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTextArea console;
	private JLabel label;

	
	public ConsolePanel() {
		setBackground(Color.WHITE);

		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setPreferredSize(new Dimension(330, 287));
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(null);
		console = new JTextArea();
		console.setEditable(false);
		console.setLineWrap(true);
		console.setRows(10);
		console.setColumns(30);
		console.setWrapStyleWord(true);
		console.setForeground(Color.WHITE);
		console.setBackground(Color.BLACK);
		console.setFont(new Font("Monospaced",Font.PLAIN,15));
		
		JScrollPane scrollPane = new JScrollPane(console);
		scrollPane.setBounds(5, 5, 319, 252);
		panel_1.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		console.setText(">Welcome.");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 263, 329, 24);
		panel_1.add(panel);
		
		JLabel lblCurrentCommad = new JLabel("Current command: ");
		panel.add(lblCurrentCommad);
		
		label = new JLabel("");
		label.setPreferredSize(new Dimension(210, 14));
		panel.add(label);

	}
	
	public void output(String text) {
		console.append("\n>" + text);
	}
	
	public void setCommand(String commmand) {
		if (commmand != null)
		this.label.setText(commmand);
	}
}
