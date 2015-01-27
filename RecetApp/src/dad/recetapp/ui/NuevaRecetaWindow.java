package dad.recetapp.ui;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.Window;

 
public class NuevaRecetaWindow extends Window implements Bindable {

	@BXML
	private NuevaRecetaWindow anadirRecetaDialog;
	@BXML
	private Button crearRecetaWindowButton, cancelarRecetaWindowButton;
	//TODO
	@BXML
	private TabPane tabPaneNuevaReceta;
	
//	@SuppressWarnings("unused")
//	private List<ComponenteReceta> componentes;
	
	
    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
    	
    	tabPaneNuevaReceta.getComponentMouseButtonListeners().add(new ComponentMouseButtonListener() {
			@Override
			public boolean mouseUp(Component arg0,
					org.apache.pivot.wtk.Mouse.Button arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseDown(Component arg0,
					org.apache.pivot.wtk.Mouse.Button arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseClick(Component arg0, org.apache.pivot.wtk.Mouse.Button arg1, int arg2, int arg3, int arg4) {
				if (tabPaneNuevaReceta.getSelectedIndex() == tabPaneNuevaReceta.getTabs().getLength()-1) {
					crearNuevoPanelComponente();
				}
				return false;
			}
		});
    	
    	cancelarRecetaWindowButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button arg0) {
				close();
			}
		});
    }

	protected void crearNuevoPanelComponente() {
//		tabPaneNuevaReceta.setTab
		//tabPaneNuevaReceta.getTabs().setTa add(new ComponenteReceta());
		System.out.println("click");
	}

}