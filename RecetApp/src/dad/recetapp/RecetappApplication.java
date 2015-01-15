package dad.recetapp;

import java.io.IOException;
import java.net.URL;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

public class RecetappApplication implements Application{
	 
	 private Window pantallaInicioDialog;
	    @Override
	    public void startup(Display display, Map<String, String> properties) throws Exception {
	    	pantallaInicioDialog = (Window) loadWindow("dad/recetapp/ui/pantallaInicio.bxml");
	    	pantallaInicioDialog.open(display);
	    	java.awt.Frame hostFrame = (java.awt.Frame)display.getDisplayHost().getParent();
	    	hostFrame.setLocationRelativeTo(null);
	    	hostFrame.setResizable(true);
	    }
	    
	    public static Window loadWindow(String bxmlFile) throws IOException, SerializationException {
			URL bxmlUrl = RecetappApplication.class.getClassLoader().getResource(bxmlFile);
			BXMLSerializer serializer = new BXMLSerializer();
			return (Window) serializer.readObject(bxmlUrl);
		}
	 
	    @Override
	    public boolean shutdown(boolean optional) {
	        if (pantallaInicioDialog != null) {
	        	pantallaInicioDialog.close();
	        }
	 
	        return false;
	    }
	 
	    @Override
	    public void suspend() {
	    }
	 
	    @Override
	    public void resume() {
	    }
}
