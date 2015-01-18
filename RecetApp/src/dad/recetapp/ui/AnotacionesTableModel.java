package dad.recetapp.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dad.recetapp.services.items.AnotacionItem;
@SuppressWarnings("serial")
public class AnotacionesTableModel extends AbstractTableModel{

	private List<AnotacionItem> anotaciones = new ArrayList<AnotacionItem>();
	private static final String [] COLUMN_NAMES = {"Descripción"};
	private static final Class<?> [] COLUMN_CLASSES = {String.class};
	
	public void setIngredientes(List<AnotacionItem> anotaciones){
		this.anotaciones = anotaciones;
		fireTableDataChanged();
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	public int getRowCount() {
		return anotaciones.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		AnotacionItem anotacion = anotaciones.get(rowIndex);
		switch(columnIndex){
		case 0: value = anotacion.getAnotaciones(); break;
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
