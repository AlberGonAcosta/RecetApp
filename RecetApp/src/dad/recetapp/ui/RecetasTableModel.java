package dad.recetapp.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dad.recetapp.services.items.RecetaItem;


@SuppressWarnings("serial")
public class RecetasTableModel extends AbstractTableModel{

	private List<RecetaItem> recetas = new ArrayList<RecetaItem>();
	private static final String [] COLUMN_NAMES = {"Nombre", "Para", "Tiempo total", "Fecha de creación", "Categoria"};
	private static final Class<?> [] COLUMN_CLASSES = {String.class, String.class, Integer.class, Date.class, String.class};
	
	public void setIngredientes(List<RecetaItem> recetas){
		this.recetas = recetas;
		fireTableDataChanged();
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	public int getRowCount() {
		return recetas.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		RecetaItem receta = recetas.get(rowIndex);
		switch(columnIndex){
		case 0: value = receta.getNombre(); break;
		case 1: value = receta.getPara(); break;
		case 2: value = receta.getTiempoTotal(); break;
		case 3: value = receta.getFechaCreacion(); break;
		case 4: value = receta.getCategoria().getDescripcion(); break;
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
