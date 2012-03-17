package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsolePanel extends JPanel {

	private JTextArea console;
	private MainFrame parent;
	private String input;
	
	public ConsolePanel(MainFrame parent) {
		this.parent = parent;
		
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
		console.setText(">Welcome.\n");
		add(console, BorderLayout.CENTER);

	}
	
	public void output(String text) {
		console.append(">" + text);
	}
}
