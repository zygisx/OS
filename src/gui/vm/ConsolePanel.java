package gui.vm;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;

public class ConsolePanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTextArea console;
	private JLabel label;

	
	public ConsolePanel() {

		setBorder(BorderFactory.createTitledBorder("Output"));
		setLayout(new BorderLayout(0, 0));
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
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		console.setText(">Welcome.");
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JLabel lblCurrentCommad = new JLabel("Current command: ");
		panel.add(lblCurrentCommad);
		
		label = new JLabel("");
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
