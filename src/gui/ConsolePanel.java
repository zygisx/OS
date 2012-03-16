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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsolePanel extends JPanel implements KeyListener {

	private JTextArea console;
	private MainFrame parent;
	private String input;
	
	public ConsolePanel(MainFrame parent) {
		this.parent = parent;
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JTextArea console = new JTextArea();
		console.setLineWrap(true);
		console.setRows(15);
		console.setColumns(30);
		console.setWrapStyleWord(true);
		console.setForeground(Color.MAGENTA);
		console.setBackground(Color.BLACK);
		console.setFont(new Font("Monospaced",Font.PLAIN,15));
		
		KeyListener keyListener = new KeyListener() {
		      public void keyPressed(KeyEvent keyEvent) {
		        printIt("Pressed", keyEvent);
		      }

		      public void keyReleased(KeyEvent keyEvent) {
		        printIt("Released", keyEvent);
		      }

		      public void keyTyped(KeyEvent keyEvent) {
		        printIt("Typed", keyEvent);
		      }

		      private void printIt(String title, KeyEvent keyEvent) {
		        int keyCode = keyEvent.getKeyCode();
		        String keyText = KeyEvent.getKeyText(keyCode);
		        System.out.println(title + " : " + keyText + " / " + keyEvent.getKeyChar());
		      }
		    };
		console.addKeyListener(this);
		console.setText(">Welcome.");
		add(console);
		
		JTextPane textPane = new JTextPane();
		textPane.setPreferredSize(new Dimension(53, 100));
		
		add(textPane);

	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
	
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			this.parent.inputAccepted(this.input);
	        System.out.println("ENTER pressed");
	    }
		else this.input += e.getKeyChar();
	}

}
