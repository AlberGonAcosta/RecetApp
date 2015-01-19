package dad.recetapp.ui;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
 
public class NuevoIngredienteDialog extends Dialog implements Bindable {

	@BXML
	private NuevoIngredienteDialog nuevoIngredienteDialog;
	@BXML
	private Button anadirIngredienteButton, cancelarIngredienteButton;
	
    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
    	
    	nuevoIngredienteDialog.setTitle("Nuevo ingrediente");

    	cancelarIngredienteButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button arg0) {
				nuevoIngredienteDialog.close();
			}
		});
    }

}