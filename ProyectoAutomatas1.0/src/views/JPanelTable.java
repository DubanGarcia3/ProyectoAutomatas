package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JPanelTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableTransitions;
	private JLabel lbTitle;
	private JScrollPane scroll;

	public JPanelTable() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode("#E3D2E2"));
		lbTitle = new JLabel("Matriz de Transiciones");
		lbTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		lbTitle.setFont(ConstansFont.fontTitle1);
		this.add(lbTitle,BorderLayout.PAGE_START);

		scroll = new JScrollPane();
		
		this.add(scroll,BorderLayout.CENTER);
	}	

	public void update(String[][] matriz) {

		DefaultTableModel tm = new DefaultTableModel(matriz.length-1, matriz[0].length) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
		String[] columHeaders = getColumnIdentifiers(matriz);
		tm.setColumnIdentifiers(columHeaders);

		setFilesHeaders(tm, matriz);

		tableTransitions = new JTable(tm) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void changeSelection(int rowIndex, int columnIndex,
					boolean toggle, boolean extend) {
				if (columnIndex == 0)
					super.changeSelection(rowIndex, columnIndex + 1, toggle,
							extend);
				else
					super.changeSelection(rowIndex, columnIndex, toggle,
							extend);
			}
		};
		tableTransitions.getColumnModel().getColumn(0).setCellRenderer(
				tableTransitions.getTableHeader().getDefaultRenderer());

		addDataTable(tm,matriz);
		scroll.setViewportView(tableTransitions);
		/**
		 * agregacion colores y fuente
		 */
		tableTransitions.setFont(ConstansFont.fontregular);
		tableTransitions.getTableHeader().setFont(ConstansFont.fontregular);
		tableTransitions.getTableHeader().setForeground(Color.WHITE);
		tableTransitions.getTableHeader().setBackground(Color.decode("#84377D"));
		scroll.setBackground(Color.decode("#E3D2E2"));

	}

	private void addDataTable(DefaultTableModel tm, String[][] matriz2) {
		for (int Fila = 1; Fila < matriz2.length; Fila++) {
			for (int columna = 1; columna < matriz2[1].length; columna++) {
				tm.setValueAt(matriz2[Fila][columna], Fila-1, columna);
				
			}
		}
	}

	private void setFilesHeaders(DefaultTableModel tm,String[][] strings) {
		for (int i = 1; i < strings.length; i++) {
			tm.setValueAt(strings[i][0], i-1, 0);
		}

	}

	private String[] getColumnIdentifiers(String[][] matriz) {
		String[] strings = new String[matriz[0].length];
		strings[0] = "";
		for (int i = 1; i < matriz[0].length; i++) {
			strings[i] = matriz[0][i]; 
		}
		return strings;
	}

}