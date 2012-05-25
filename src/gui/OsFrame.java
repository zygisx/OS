package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class OsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	}
}
