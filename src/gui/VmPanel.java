package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import machine.RealMachineRegisters;
import machine.Realmachine;
import machine.VirtualMachine;
import machine.Word;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;

public class VmPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private JTable table;
	private JLabel labelR1;
	private JLabel labelR2;
	private JLabel labelIC;
	private JLabel labelSF;
	private JLabel labelPLR;
	private JLabel labelMODE;
	private JLabel labelTIMER;
	private JLabel labelCH1;
	private JLabel labelCH2;
	private JLabel labelCH3;
	private JLabel labelCH4;
	private JLabel labelSI;
	private JLabel labelPI;
	private JLabel labelIOI;
	private JLabel labelTI;
	
	private ConsolePanel consolePanel;

	VmPanel(int id) {
		setBackground(Color.WHITE);
		this.id = id;
		setLayout(new BorderLayout(0, 0));
		
		JPanel memPanel = new JPanel();
		memPanel.setBackground(Color.WHITE);
		table = new JTable(new MemoryTableModel(id));
		table.setPreferredSize(new Dimension(575, 300));
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(575, 282));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table.setShowVerticalLines(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		memPanel.add(scrollPane);
		add(memPanel, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(575, 100));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		JPanel registersPanel = new JPanel();
		registersPanel.setBounds(new Rectangle(10, 10, 575, 80));
		panel.add(registersPanel);
		//registersPanel.setSize(new Dimension(575, 100));
		registersPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		registersPanel.setBackground(Color.WHITE);
		registersPanel.setLayout(new GridLayout(4, 8));
		
		
		
		JLabel lblR1 = new JLabel("R1: ");
		lblR1.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblR1);
		
		this.labelR1 = new JLabel("");
		registersPanel.add(labelR1);
		
		JLabel lblR2 = new JLabel("R2: ");
		lblR2.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblR2);
		
		this.labelR2 = new JLabel("");
		registersPanel.add(labelR2);
		
		JLabel lblIC = new JLabel("IC: ");
		lblIC.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblIC);
		
		this.labelIC = new JLabel("");
		registersPanel.add(labelIC);
		
		JLabel lblSF = new JLabel("SF: ");
		lblSF.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblSF);
		
		this.labelSF = new JLabel("");
		registersPanel.add(labelSF);
		
		JLabel lblPLR = new JLabel("PLR: ");
		lblPLR.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblPLR);
		
		this.labelPLR = new JLabel("");
		registersPanel.add(labelPLR);
		
		JLabel lblMODE = new JLabel("MODE: ");
		lblMODE.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblMODE);
		
		this.labelMODE = new JLabel("");
		registersPanel.add(labelMODE);
		
		JLabel lblTIMER = new JLabel("TIMER: ");
		lblTIMER.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblTIMER);
		
		this.labelTIMER = new JLabel("");
		registersPanel.add(labelTIMER);
		
		JLabel lblCH1 = new JLabel("CH1: ");
		lblCH1.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblCH1);
		
		this.labelCH1 = new JLabel("");
		registersPanel.add(labelCH1);
		
		JLabel lblCH2 = new JLabel("CH2: ");
		lblCH2.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblCH2);
		
		this.labelCH2 = new JLabel("");
		registersPanel.add(labelCH2);
		
		JLabel lblCH3 = new JLabel("CH3: ");
		lblCH3.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblCH3);
		
		this.labelCH3 = new JLabel("");
		registersPanel.add(labelCH3);
		
		JLabel lblCH4 = new JLabel("CH4: ");
		lblCH4.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblCH4);
		
		this.labelCH4 = new JLabel("");
		registersPanel.add(labelCH4);
		
		JLabel lblSI = new JLabel("SI: ");
		lblSI.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblSI);
		
		this.labelSI = new JLabel("");
		registersPanel.add(labelSI);
		
		JLabel lblPI = new JLabel("PI: ");
		lblPI.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblPI);
		
		this.labelPI = new JLabel("");
		registersPanel.add(labelPI);
		
		JLabel lblIOI = new JLabel("IOI: ");
		lblIOI.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblIOI);
		
		this.labelIOI = new JLabel("");
		registersPanel.add(labelIOI);
		
		JLabel lblTI = new JLabel("TI: ");
		lblTI.setHorizontalAlignment(SwingConstants.RIGHT);
		registersPanel.add(lblTI);
		
		this.labelTI = new JLabel("");
		registersPanel.add(labelTI);
		
		consolePanel = new ConsolePanel();
		add(consolePanel, BorderLayout.EAST);
		
		
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setCommand(String command) {
		consolePanel.setCommand(command);
	}
	
	public void update() {
		((AbstractTableModel) this.table.getModel()).fireTableDataChanged();
		
		this.labelR1.setText(RealMachineRegisters.getR1().getStringValue());
		this.labelR2.setText(RealMachineRegisters.getR2().getStringValue());
		this.labelIC.setText(Integer.toString(RealMachineRegisters.getIC()));
		this.labelSF.setText(Integer.toString(RealMachineRegisters.getSF()));
		this.labelPLR.setText(Integer.toString(RealMachineRegisters.getPLR()));
		this.labelMODE.setText(Integer.toString(RealMachineRegisters.getMODE()));
		this.labelTIMER.setText(Integer.toString(RealMachineRegisters.getTIMER()));
		this.labelCH1.setText(Integer.toString(RealMachineRegisters.getCH1()));
		this.labelCH2.setText(Integer.toString(RealMachineRegisters.getCH2()));
		this.labelCH3.setText(Integer.toString(RealMachineRegisters.getCH3()));
		this.labelCH4.setText(Integer.toString(RealMachineRegisters.getCH4()));
		this.labelSI.setText(Integer.toString(RealMachineRegisters.getSI()));
		this.labelPI.setText(Integer.toString(RealMachineRegisters.getPI()));
		this.labelIOI.setText(Integer.toString(RealMachineRegisters.getIOI()));
		this.labelTI.setText(Integer.toString(RealMachineRegisters.getTI()));
	}

}


class MemoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnNames = {
		"No.", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"
	};
	private Word[] memory;
	private int id;
	
	public MemoryTableModel(int id) {
		this.memory = new Word[Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE];
		for (int i = 0; i < Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE; i++) {
			this.memory[i] = new Word(Integer.toString(i));
		}
		this.id = id;
	}
	
	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE;
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0) return Integer.toHexString(row).toUpperCase();
		//System.out.println((row)*0x10 + col-1);
		return Realmachine.getVirtualMachine(id).getWord((row)*0x10 + col-1);
		//return 0;
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	public boolean isCellEditable(int row, int col) {
        return false;
    }
}
