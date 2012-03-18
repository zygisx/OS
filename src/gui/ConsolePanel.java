package gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JTextArea;
import java.awt.Color;

public class ConsolePanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTextArea console;
	
	public ConsolePanel() {

		setBorder(BorderFactory.createTitledBorder("Output"));
		console = new JTextArea();
		console.setEditable(false);
		console.setLineWrap(true);
		console.setRows(10);
		console.setColumns(30);
		console.setWrapStyleWord(true);
		console.setForeground(Color.WHITE);
		console.setBackground(Color.BLACK);
		console.setFont(new Font("Monospaced",Font.PLAIN,15));
		
		setLayout(new BorderLayout(5, 10));
		console.setText(">Welcome.");
		add(console, BorderLayout.CENTER);

	}
	
	public void output(String text) {
		console.append("\n>" + text);
	}
}
