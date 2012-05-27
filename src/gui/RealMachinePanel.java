package gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;

import machine.Realmachine;
import machine.Word;

public class RealMachinePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	
	
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public RealMachinePanel() {

		
		
		JPanel memPanel = new JPanel();
		table = new JTable(new RealMachineMemoryTableModel());
		table.setPreferredSize(new Dimension(575, 4100));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(595, 300));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table.setShowVerticalLines(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		memPanel.add(scrollPane);
		
		memPanel.setBorder(BorderFactory.createTitledBorder("Memory"));
		add(memPanel);
		
		
	}
	
	public void update() {
		((AbstractTableModel) this.table.getModel()).fireTableDataChanged();
	}

}


class RealMachineMemoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnNames = {
		"No.", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"
	};
	private Word[] memory;
	
	public RealMachineMemoryTableModel() {
		this.memory = new Word[Realmachine.REAL_MEMORY_SIZE];
		for (int i = 0; i < Realmachine.REAL_MEMORY_SIZE; i++) {
			this.memory[i] = new Word(Integer.toString(i));
		}
	}
	
	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Realmachine.BLOCK_COUNT;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (arg1 == 0) return Integer.toHexString(arg0).toUpperCase();
		return Realmachine.getBlock(arg0)[arg1-1];
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	public boolean isCellEditable(int row, int col) {
        return false;
    }

}
