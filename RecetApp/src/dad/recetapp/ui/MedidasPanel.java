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
import dad.recetapp.services.items.MedidaItem;
 
public class MedidasPanel extends TablePane implements Bindable {

	@BXML
	private TableView tableView;
	private org.apache.pivot.collections.List<MedidaItem> variables;
	private List<MedidaItem> lista;
	@BXML
	private Button anadirMedidaButton;
	@BXML
	private Button eliminarMedidaButton;
	@BXML
	private TextInput nombreText;
	@BXML
	private TextInput abreviaturaText;

    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
    	variables = new org.apache.pivot.collections.ArrayList<MedidaItem>();

		try {
			lista = ServiceLocator.getMedidasService().listarMedidas();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		for (MedidaItem l : lista) {
			variables.add(l);
		}
		tableView.setTableData(variables);
		
		anadirMedidaButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				onAnadirMedidaButtonActionPerformed();
			}
		});
		eliminarMedidaButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				onEliminarMedidaButtonActionPerformed();
			}
		});
    }

	protected void onAnadirMedidaButtonActionPerformed() {
		MedidaItem nuevaMedida = new MedidaItem();
		nuevaMedida.setId(1l);
		nuevaMedida.setNombre(nombreText.getText());
		nuevaMedida.setAbreviatura(abreviaturaText.getText());
		variables.add(nuevaMedida);
		try {
			ServiceLocator.getMedidasService().crearMedida(nuevaMedida);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		nombreText.setText("");
		abreviaturaText.setText("");
	}

	protected void onEliminarMedidaButtonActionPerformed() {
		Sequence<?> seleccionados = tableView.getSelectedRows();
		for (int i = 0; i < seleccionados.getLength(); i++) {
			MedidaItem medidaSeleccionada = (MedidaItem) seleccionados.get(i);
			variables.remove(medidaSeleccionada);
			try {
				ServiceLocator.getMedidasService().eliminarMedida(medidaSeleccionada.getId());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}