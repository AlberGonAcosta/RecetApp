package dad.recetapp.ui;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Window;
 
public class EditarRecetaWindow extends Window implements Bindable {

	@BXML
	private EditarRecetaWindow anadirRecetaDialog;
	@BXML
	private Button editarRecetaWindowButton, cancelarRecetaWindowButton;
    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

    	cancelarRecetaWindowButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button arg0) {
				close();
			}
		});
    }

}