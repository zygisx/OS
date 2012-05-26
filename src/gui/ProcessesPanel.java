package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;

import os.Kernel;
import os.TaskManager;

public class ProcessesPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	public ProcessesPanel() {
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBackground(Color.WHITE);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(900, 350));
		table.setAlignmentY(Component.TOP_ALIGNMENT);
		table.setAlignmentX(Component.LEFT_ALIGNMENT);
		table.setModel(new ProcessesTableModel());
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(900, 380));
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table.setPreferredSize(new Dimension(900, 380));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBackground(Color.WHITE);
		
	}
	
	public void update() {
		((AbstractTableModel) this.table.getModel()).fireTableDataChanged();
	}

}

class ProcessesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnNames = {
		"Id", "Parent", "Missing Resource", "Status", "Supervisor mode", "Priority"
	};
	
	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Kernel.getProcessesCount();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
//		if (arg1 == 0) return Integer.toHexString(arg0).toUpperCase();
		return Kernel.getProcessesListValue(arg0, arg1);
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	public boolean isCellEditable(int row, int col) {
        return false;
    }

}
