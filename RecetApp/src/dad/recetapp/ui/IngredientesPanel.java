package dad.recetapp.ui;

import java.net.URL;
import java.util.List;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;

import dad.recetapp.services.ServiceException;
import dad.recetapp.services.ServiceLocator;
import dad.recetapp.services.items.TipoAnotacionItem;
import dad.recetapp.services.items.TipoIngredienteItem;

public class IngredientesPanel extends TablePane implements Bindable {

	@BXML
	private TableView tableView;
	private org.apache.pivot.collections.List<TipoIngredienteItem> variables;
	private List<TipoIngredienteItem> lista;
	@BXML
	private Button anadirIngredienteButton;
	@BXML
	private Button eliminarIngredienteButton;
	@BXML
	private TextInput nombreText;


	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		variables = new org.apache.pivot.collections.ArrayList<TipoIngredienteItem>();

		try {
			lista = ServiceLocator.getTiposIngredientesService().listarTiposIngredientes();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		for (TipoIngredienteItem l : lista) {
			variables.add(l);
		}
		tableView.setTableData(variables);
		anadirIngredienteButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				onAnadirIngredienteButtonActionPerformed();
			}
		});

		eliminarIngredienteButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				onEliminarIngredienteButtonActionPerformed();
			}
		});
	}


	protected void onAnadirIngredienteButtonActionPerformed() {
		TipoIngredienteItem nuevoTipoIngrediente = new TipoIngredienteItem();
		nuevoTipoIngrediente.setId(1l);
		nuevoTipoIngrediente.setNombre(nombreText.getText());
		variables.add(nuevoTipoIngrediente);
		try {
			ServiceLocator.getTiposIngredientesService().crearTipoIngrediente(nuevoTipoIngrediente);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		nombreText.setText("");
	}


	protected void onEliminarIngredienteButtonActionPerformed() {
		Sequence<?> seleccionados = tableView.getSelectedRows();
		for (int i = 0; i < seleccionados.getLength(); i++) {
			TipoIngredienteItem tipoIngredienteSeleccionado = (TipoIngredienteItem) seleccionados.get(i);
			variables.remove(tipoIngredienteSeleccionado);
			try {
				ServiceLocator.getTiposIngredientesService().eliminarTipoIngrediente(tipoIngredienteSeleccionado.getId());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}