package dad.recetapp.ui;

import java.net.URL;
import java.util.List;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TableView;

import dad.recetapp.services.ServiceException;
import dad.recetapp.services.ServiceLocator;
import dad.recetapp.services.items.CategoriaItem;

public class CategoriasPanel extends TablePane implements Bindable {

	@BXML
	private TableView tableView;
	private org.apache.pivot.collections.List<CategoriaItem> variables;
	private List<CategoriaItem> lista;

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		variables = new org.apache.pivot.collections.ArrayList<CategoriaItem>();

		try {
			lista = ServiceLocator.getCategoriasService().listarCategorias();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		for (CategoriaItem l : lista) {
			variables.add(l);
		}
		tableView.setTableData(variables);
	}
}