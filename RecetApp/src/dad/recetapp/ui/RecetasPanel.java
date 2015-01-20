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
import dad.recetapp.services.items.RecetaListItem;
 
public class RecetasPanel extends TablePane implements Bindable {

	@BXML
	private TableView tableView;
	private org.apache.pivot.collections.List<RecetaListItem> variables;
	private List<RecetaListItem> lista;

    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
    	variables = new org.apache.pivot.collections.ArrayList<RecetaListItem>();

		try {
			lista = ServiceLocator.getRecetasService().listarRecetas();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		for (RecetaListItem l : lista) {
			variables.add(l);
		}
		tableView.setTableData(variables);
    }
 
}