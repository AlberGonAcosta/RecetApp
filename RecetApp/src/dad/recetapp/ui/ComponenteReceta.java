package dad.recetapp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.DialogCloseListener;
import org.apache.pivot.wtk.FillPane;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;

import dad.recetapp.RecetappApplication;
import dad.recetapp.services.items.IngredienteItem;
import dad.recetapp.services.items.InstruccionItem;
import dad.recetapp.services.items.SeccionItem;

public class ComponenteReceta extends FillPane implements Bindable {

	@BXML
	private ComponenteReceta anotacionesPanel;
	@BXML
	private Button anadirIngredienteButton, anadirInstruccionButton;
	@BXML
	private Button editarIngredienteButton, editarInstruccionButton;
	@BXML
	private Button eliminarIngredienteButton, eliminarInstruccionButton;
	@BXML
	private TextInput seccionText;
	@BXML
	private Button eliminarPestanaButton;
	@BXML
	public TableView ingredientesTable;
	@BXML
	public static TableView instruccionesTable;
	public org.apache.pivot.collections.List<IngredienteItem> variablesIngredientes;
	public static org.apache.pivot.collections.List<InstruccionItem> variablesInstrucciones;
	
	public static IngredienteItem nuevoIngrediente;
	
	@Override
	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {
		
		variablesIngredientes = new org.apache.pivot.collections.ArrayList<IngredienteItem>();
		variablesInstrucciones = new org.apache.pivot.collections.ArrayList<InstruccionItem>();

		seccionText.getComponentKeyListeners().add(
				new ComponentKeyListener.Adapter() {
					@Override
					public boolean keyTyped(Component arg0, char arg1) {
						try {
							NuevaRecetaWindow.cambiarTituloPestana(seccionText
									.getText());
							NuevaRecetaWindow.tabPaneNuevaReceta.repaint();
						} catch (NullPointerException e) {
						}
						try {
							EditarRecetaWindow.cambiarTituloPestana(seccionText
									.getText());
							EditarRecetaWindow.tabPaneEditarReceta.repaint();
						} catch (NullPointerException e) {
						}
						return false;
					}

				});

		eliminarPestanaButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					@Override
					public void buttonPressed(Button button) {
						try {
							NuevaRecetaWindow.eliminarPestanaActual();
						} catch (NullPointerException e) {
						}
						try {
							EditarRecetaWindow.eliminarPestanaActual();

						} catch (NullPointerException e) {
						}
					}
				});

		anadirIngredienteButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					@Override
					public void buttonPressed(Button button) {
						anadirIngrediente();
					}
				});

		anadirInstruccionButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					@Override
					public void buttonPressed(Button button) {
						anadirInstruccion();
					}
				});

		editarIngredienteButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					@Override
					public void buttonPressed(Button button) {
						editarIngrediente();
					}
				});

		editarInstruccionButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					@Override
					public void buttonPressed(Button button) {
						editarInstruccion();
					}
				});

		eliminarIngredienteButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					@Override
					public void buttonPressed(Button button) {
						eliminarIngrediente();
					}
				});

		eliminarInstruccionButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					@Override
					public void buttonPressed(Button button) {
						eliminarInstruccion();
					}
				});
	}

	private void editarIngrediente() {

		Sequence<?> seleccionados = ingredientesTable.getSelectedRows();
		if (seleccionados.getLength() != 1) {
			Prompt error = new Prompt("Debe seleccionar un ingrediente.");
			error.open(this.getWindow(), new SheetCloseListener() {
				public void sheetClosed(Sheet sheet) {
				}
			});
		} else {
			EditarIngredienteDialog editarIngredienteDialog = null;
			try {
				editarIngredienteDialog = (EditarIngredienteDialog) RecetappApplication
						.loadWindow("dad/recetapp/ui/EditarIngredienteDialog.bxml");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SerializationException e) {
				e.printStackTrace();
			}
			editarIngredienteDialog.open(getDisplay());
		}

	}

	private void editarInstruccion() {

		Sequence<?> seleccionados = instruccionesTable.getSelectedRows();
		if (seleccionados.getLength() != 1) {
			Prompt error = new Prompt("Debe seleccionar una instrucción.");
			error.open(this.getWindow(), new SheetCloseListener() {
				public void sheetClosed(Sheet sheet) {
				}
			});
		} else {
			EditarInstruccionDialog editarInstruccionDialog = null;
			try {
				editarInstruccionDialog = (EditarInstruccionDialog) RecetappApplication.loadWindow("dad/recetapp/ui/EditarInstruccionDialog.bxml");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SerializationException e) {
				e.printStackTrace();
			}
			editarInstruccionDialog.open(getDisplay());
		}
	}

	private void anadirInstruccion() {
		NuevaInstruccionDialog nuevaInstruccionDialog = null;
		try {
			nuevaInstruccionDialog = (NuevaInstruccionDialog) RecetappApplication.loadWindow("dad/recetapp/ui/NuevaInstruccionDialog.bxml");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		nuevaInstruccionDialog.open(getDisplay());
	}

	private void anadirIngrediente() {
		NuevoIngredienteDialog nuevoIngredienteDialog = null;
		try {
			nuevoIngredienteDialog = (NuevoIngredienteDialog) RecetappApplication.loadWindow("dad/recetapp/ui/NuevoIngredienteDialog.bxml");		
			nuevoIngredienteDialog.open(this.getWindow(), new DialogCloseListener() {
				@Override
				public void dialogClosed(Dialog d, boolean arg1) {
					NuevoIngredienteDialog nid = (NuevoIngredienteDialog) d;
					
					if (nid.getAceptar()) {
						variablesIngredientes.add(nid.getIngrediente());
						ingredientesTable.setTableData(variablesIngredientes);
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerializationException e) {
			e.printStackTrace();
		}
	}

	private void eliminarIngrediente() {
		Sequence<?> seleccionados = ingredientesTable.getSelectedRows();
		if (seleccionados.getLength() == 0) {
			Prompt error = new Prompt("Debe seleccionar un ingrediente.");
			error.open(this.getWindow(), new SheetCloseListener() {
				public void sheetClosed(Sheet sheet) {
				}
			});
		} else {
			for (int i = 0; i < seleccionados.getLength(); i++) {
				IngredienteItem ingredienteSeleccionado = (IngredienteItem) seleccionados
						.get(i);
				variablesIngredientes.remove(ingredienteSeleccionado);
			}
		}
	}

	private void eliminarInstruccion() {
		Sequence<?> seleccionados = instruccionesTable.getSelectedRows();
		if (seleccionados.getLength() == 0) {
			Prompt error = new Prompt("Debe seleccionar una instrucción.");
			error.open(this.getWindow(), new SheetCloseListener() {
				public void sheetClosed(Sheet sheet) {
				}
			});
		} else {
			for (int i = 0; i < seleccionados.getLength(); i++) {
				InstruccionItem instruccionSeleccionada = (InstruccionItem) seleccionados
						.get(i);
				variablesInstrucciones.remove(instruccionSeleccionada);
			}
		}
	}

	// TODO
	public SeccionItem getSeccion() {
		SeccionItem seccion = new SeccionItem();
		seccion.setNombre(seccionText.getText());
		seccion.setIngredientes(convertirListIngredientes(variablesIngredientes));
		seccion.setInstrucciones(convertirListInstrucciones(variablesInstrucciones));

		return seccion;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static java.util.List<IngredienteItem> convertirListIngredientes(
			org.apache.pivot.collections.List listaUtil) {
		List listaApache = new ArrayList();

		for (int i = 0; i < listaUtil.getLength(); i++) {
			listaApache.add(listaUtil.get(i));
		}
		return listaApache;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static java.util.List<InstruccionItem> convertirListInstrucciones(
			org.apache.pivot.collections.List listaUtil) {
		List listaApache = new ArrayList();

		for (int i = 0; i < listaUtil.getLength(); i++) {
			listaApache.add(listaUtil.get(i));
		}
		return listaApache;
	}
}