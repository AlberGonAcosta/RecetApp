package dad.recetapp.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dad.recetapp.services.items.MedidaItem;

@SuppressWarnings("serial")
public class MedidasTableModel extends AbstractTableModel{

	private List<MedidaItem> medidas = new ArrayList<MedidaItem>();
	private static final String [] COLUMN_NAMES = {"Nombre", "Abreviatura"};
	private static final Class<?> [] COLUMN_CLASSES = {String.class, String.class};
	
	public void setIngredientes(List<MedidaItem> medidas){
		this.medidas = medidas;
		fireTableDataChanged();
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	public int getRowCount() {
		return medidas.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		MedidaItem medida = medidas.get(rowIndex);
		switch(columnIndex){
		case 0: value = medida.getNombre(); break;
		case 1: value = medida.getAbreviatura();break;
		}
		return value;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
