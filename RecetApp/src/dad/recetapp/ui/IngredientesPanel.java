package dad.recetapp.ui;

import java.io.IOException;
import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.TablePane;

import dad.recetapp.RecetappApplication;
 
public class IngredientesPanel extends TablePane implements Bindable {

	@BXML
	private IngredientesPanel ingredientesPanel;
	@BXML
	private Button anadirInstruccionButton;
	@BXML
	private Button eliminarInstruccionButton;
 

    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
    	anadirInstruccionButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	NuevaInstruccionDialog nuevaInstruccionDialog = null;
            	
        		try {
        			nuevaInstruccionDialog = (NuevaInstruccionDialog) RecetappApplication.loadWindow("dad/recetapp/ui/NuevaInstruccionDialog.bxml");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (SerializationException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		        		
        		nuevaInstruccionDialog.open(getDisplay());
        		
            }
        });
    	
    	eliminarInstruccionButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	//TODO
            	EditarInstruccionDialog editarInstruccionDialog = null;
            	
        		try {
        			editarInstruccionDialog = (EditarInstruccionDialog) RecetappApplication.loadWindow("dad/recetapp/ui/EditarInstruccionDialog.bxml");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (SerializationException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		        		
        		editarInstruccionDialog.open(getDisplay());
        		
            }
        });
    }

}