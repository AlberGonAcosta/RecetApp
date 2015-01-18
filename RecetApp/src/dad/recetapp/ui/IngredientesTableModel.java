package dad.recetapp.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dad.recetapp.services.items.IngredienteItem;

@SuppressWarnings("serial")
public class IngredientesTableModel extends AbstractTableModel {

	private List<IngredienteItem> ingredientes = new ArrayList<IngredienteItem>();
	private static final String [] COLUMN_NAMES = {"Nombre"};
	private static final Class<?> [] COLUMN_CLASSES = {String.class};
	
	public void setIngredientes(List<IngredienteItem> ingrediente){
		this.ingredientes = ingrediente;
		fireTableDataChanged();
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	public int getRowCount() {
		return ingredientes.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		IngredienteItem ingrediente = ingredientes.get(rowIndex);
		switch(columnIndex){
		case 0: value = ingrediente.getTipo().getNombre(); break;
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
