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

public class AnotacionesPanel extends TablePane implements Bindable {

	@BXML
	private TableView tableView;
	private org.apache.pivot.collections.List<TipoAnotacionItem> variables;
	private List<TipoAnotacionItem> lista;
	@BXML
	private Button anadirAnotacionButton;
	@BXML
	private Button eliminarAnotacionButton;
	@BXML
	private TextInput nombreText;

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		variables = new org.apache.pivot.collections.ArrayList<TipoAnotacionItem>();

		try {
			lista = ServiceLocator.getTiposAnotacionesService().listarTiposAnotaciones();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		for (TipoAnotacionItem l : lista) {
			variables.add(l);
		}
		tableView.setTableData(variables);

		anadirAnotacionButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				onAnadirAnotacionButtonActionPerformed();
			}
		});
		eliminarAnotacionButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				onEliminarAnotacionButtonActionPerformed();
			}
		});
	}

	protected void onAnadirAnotacionButtonActionPerformed() {
		TipoAnotacionItem nuevaAnotacion = new TipoAnotacionItem();
		nuevaAnotacion.setDescripcion(nombreText.getText());
		variables.add(nuevaAnotacion);
		try {
			ServiceLocator.getTiposAnotacionesService().crearTipoAnotacion(nuevaAnotacion);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		nombreText.setText("");
	}

	protected void onEliminarAnotacionButtonActionPerformed() {
		Sequence<?> seleccionados = tableView.getSelectedRows();
		for (int i = 0; i < seleccionados.getLength(); i++) {
			try {
				TipoAnotacionItem anotacionSeleccionada = (TipoAnotacionItem) seleccionados.get(i);
				variables.remove(anotacionSeleccionada);
				ServiceLocator.getTiposAnotacionesService().eliminarTipoAnotacion(anotacionSeleccionada.getId());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
		
//		StringBuffer mensaje = new StringBuffer();
//		mensaje.append("�Desea eliminar las siguientes anotaciones?\n\n");
//		
//		Sequence<?> seleccionados = tableView.getSelectedRows();
//		for (int i = 0; i < seleccionados.getLength(); i++) {
//			TipoAnotacionItem variableSeleccionada = (TipoAnotacionItem) seleccionados.get(i);
//			mensaje.append(" - " + variableSeleccionada.getDescripcion() + "\n");
//		}
//		
//		Prompt confirmar = new Prompt(MessageType.WARNING, mensaje.toString(), new ArrayList<String>("S�", "No"));
//		confirmar.open(this, new SheetCloseListener() {
//			public void sheetClosed(Sheet sheet) {
//				
//				if (confirmar.getResult() && confirmar.getSelectedOption().equals("S�")) {
//					Sequence<?> seleccionados = tableView.getSelectedRows();
//					for (int i = 0; i < seleccionados.getLength(); i++) {
//						variables.remove((TipoAnotacionItem)seleccionados.get(i));
//					}
//				}
//				
//			}
//		});
	}

}