package gui.vm;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;

import machine.RealMachineRegisters;
import machine.Realmachine;
import machine.Word;

import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class ComponentsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public ComponentsPanel() {
		
		setBorder(BorderFactory.createTitledBorder("Component"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel memPanel = new JPanel();
		table = new JTable(new MemoryTableModel());
		table.setPreferredSize(new Dimension(575, 4100));
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(595, 300));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//table.setPreferredSize(new Dimension(1500, 5000));
		table.setShowVerticalLines(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		memPanel.add(scrollPane);
		
		memPanel.setBorder(BorderFactory.createTitledBorder("Memory"));
		add(memPanel);
		
		JPanel registersPanel = new JPanel();
		registersPanel.setBorder(BorderFactory.createTitledBorder("Registers"));
		
		
		add(registersPanel);
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
	
	public MemoryTableModel() {
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
