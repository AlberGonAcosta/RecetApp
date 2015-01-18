package dad.recetapp.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dad.recetapp.services.items.CategoriaItem;

@SuppressWarnings("serial")
public class CategoriasTableModel extends AbstractTableModel{

	private List<CategoriaItem> categorias = new ArrayList<CategoriaItem>();
	private static final String [] COLUMN_NAMES = {"Descripción"};
	private static final Class<?> [] COLUMN_CLASSES = {String.class};
	
	public void setIngredientes(List<CategoriaItem> categorias){
		this.categorias = categorias;
		fireTableDataChanged();
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	public int getRowCount() {
		return categorias.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		CategoriaItem categoria = categorias.get(rowIndex);
		switch(columnIndex){
		case 0: value = categoria.getDescripcion(); break;
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
