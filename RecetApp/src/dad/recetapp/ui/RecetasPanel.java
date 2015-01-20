package dad.recetapp.ui;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TableView;

import dad.recetapp.services.ServiceException;
import dad.recetapp.services.ServiceLocator;
import dad.recetapp.services.items.CategoriaItem;
import dad.recetapp.services.items.RecetaListItem;
 
public class RecetasPanel extends TablePane implements Bindable {

	@BXML
	private TableView tableView;
	private org.apache.pivot.collections.List<RecetaListItem> variables;
	private org.apache.pivot.collections.List<RecetaListItem> lista;
	@BXML
	private ListButton categoriasListButton;
	private org.apache.pivot.collections.List<CategoriaItem> categoriasBD;
	private org.apache.pivot.collections.List<CategoriaItem> categoriasCombo;
	@BXML
	private Button anadirRecetaButton;
	@BXML
	private Button eliminarRecetaButton;
	@BXML
	private Button editarRecetaButton;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
    	variables = new org.apache.pivot.collections.ArrayList<RecetaListItem>();

		try {
			lista = convertirList(ServiceLocator.getRecetasService().listarRecetas());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		for (RecetaListItem l : lista) {
			variables.add(l);
		}
		tableView.setTableData(variables);
		
		try {
			categoriasBD = convertirList(ServiceLocator.getCategoriasService().listarCategorias());
			CategoriaItem categoriaTitle = new CategoriaItem();
			categoriaTitle.setId(null);
			categoriaTitle.setDescripcion("<Categorías>");
			categoriasCombo = new ArrayList();
			categoriasCombo.add(categoriaTitle);
			for(int i = 0; i<categoriasBD.getLength(); i++){
				categoriasCombo.add(categoriasBD.get(i));
			}
			categoriasListButton.setListData(categoriasCombo);
			categoriasListButton.setSelectedItem(categoriaTitle);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
//		anadirRecetaButton.getButtonPressListeners().add(new ButtonPressListener() {
//			@Override
//			public void buttonPressed(Button button) {
//				onAnadirRecetaButtonActionPerformed();
//			}
//		});
		
		eliminarRecetaButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				onEliminarRecetaButtonActionPerformed();
			}
		});
//		
//		editarRecetaButton.getButtonPressListeners().add(new ButtonPressListener() {
//			@Override
//			public void buttonPressed(Button button) {
//				onEditarRecetaButtonActionPerformed();
//			}
//		});
    }

	protected void onEliminarRecetaButtonActionPerformed() {
		StringBuffer mensaje = new StringBuffer();
		mensaje.append("¿Desea eliminar las siguientes recetas?\n\n");
		
		Sequence<?> seleccionados = tableView.getSelectedRows();
		for (int i = 0; i < seleccionados.getLength(); i++) {
			RecetaListItem recetaSeleccionada = (RecetaListItem) seleccionados.get(i);
			mensaje.append(" - " + recetaSeleccionada.getNombre() + "\n");
		}
		
		Prompt confirmar = new Prompt(MessageType.WARNING, mensaje.toString(), new ArrayList<String>("Sí", "No"));
		confirmar.open(this.getWindow(), new SheetCloseListener() {
			public void sheetClosed(Sheet sheet) {
				
				if (confirmar.getResult() && confirmar.getSelectedOption().equals("Sí")) {
					Sequence<?> seleccionados = tableView.getSelectedRows();
					for (int i = 0; i < seleccionados.getLength(); i++) {
						try {
							RecetaListItem recetaSeleccionada = (RecetaListItem) seleccionados.get(i);
							variables.remove(recetaSeleccionada);
							ServiceLocator.getRecetasService().eliminarReceta(recetaSeleccionada.getId());
						} catch (ServiceException e) {
							e.printStackTrace();
						}
					}	
				}			
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected org.apache.pivot.collections.List convertirList(java.util.List<?> listaUtil){
		org.apache.pivot.collections.List listaApache = new org.apache.pivot.collections.ArrayList();
		for(int i = 0; i<listaUtil.size(); i++){
			listaApache.add(listaUtil.get(i));
		}
		return listaApache;
	}
 
}