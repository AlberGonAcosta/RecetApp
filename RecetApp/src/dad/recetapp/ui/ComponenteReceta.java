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
import org.apache.pivot.wtk.FillPane;

import dad.recetapp.RecetappApplication;
 
public class ComponenteReceta extends FillPane implements Bindable {

	@BXML
	private ComponenteReceta anotacionesPanel;
	@BXML
	private Button anadirIngredienteButton, anadirInstruccionButton;
	@BXML
	private Button editarIngredienteButton, editarInstruccionButton;
	@BXML
	private Button eliminarIngredienteButton, eliminarInstruccionButton;
	
	
    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
    	anadirIngredienteButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	//TODO
            	NuevoIngredienteDialog nuevoIngredienteDialog = null;
            	
        		try {
        			nuevoIngredienteDialog = (NuevoIngredienteDialog) RecetappApplication.loadWindow("dad/recetapp/prueba/NuevoIngredienteDialog.bxml");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (SerializationException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		        		
        		nuevoIngredienteDialog.open(getDisplay());
        		
            }
        });
    	
    	anadirInstruccionButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	//TODO
            	NuevaInstruccionDialog nuevaInstruccionDialog = null;
            	
        		try {
        			nuevaInstruccionDialog = (NuevaInstruccionDialog) RecetappApplication.loadWindow("dad/recetapp/prueba/NuevaInstruccionDialog.bxml");
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
    	
    	editarIngredienteButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	//TODO
            	EditarIngredienteDialog editarIngredienteDialog = null;
            	
        		try {
        			editarIngredienteDialog = (EditarIngredienteDialog) RecetappApplication.loadWindow("dad/recetapp/prueba/EditarIngredienteDialog.bxml");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (SerializationException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		        		
        		editarIngredienteDialog.open(getDisplay());
        		
            }
        });
    	
    	editarInstruccionButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	//TODO
            	EditarInstruccionDialog editarInstruccionDialog = null;
            	
        		try {
        			editarInstruccionDialog = (EditarInstruccionDialog) RecetappApplication.loadWindow("dad/recetapp/prueba/EditarInstruccionDialog.bxml");
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
    	
    	eliminarIngredienteButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	//TODO
            	System.out.println("Eliminar ingrediente.");
        		
            }
        });
    	
    	eliminarInstruccionButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	//TODO
            	System.out.println("Eliminar instrucción.");
            }
        });
    }
 
}