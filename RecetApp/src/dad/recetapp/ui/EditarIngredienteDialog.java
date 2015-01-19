package dad.recetapp.ui;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
 
public class EditarIngredienteDialog extends Dialog implements Bindable {

	@BXML
	private EditarIngredienteDialog editarIngredienteDialog;
	@BXML
	private Button editarIngredienteDialogButton, cancelarIngredienteDialogButton;
	
    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
    	
    	editarIngredienteDialog.setTitle("Editar ingrediente");

    	cancelarIngredienteDialogButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button arg0) {
				editarIngredienteDialog.close();
			}
		});
    }

}