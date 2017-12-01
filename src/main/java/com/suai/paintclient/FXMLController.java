/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suai.paintclient;

import com.suai.multiuserpaint.updatercanvas.UpdaterCanvas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author nikit
 */
public class FXMLController implements Initializable {

    
    @FXML
    private Canvas canvas;
    
    @FXML
    private ColorPicker colorPicker;
    
    private UpdaterCanvas updater;
    
    @FXML
    public void presed(MouseEvent event) throws IOException
    {
        Double x = event.getX();
        Double y = event.getY();
        String action[] = new String[3];
        action[0] = "OnMousePressed";
        action[1] = x.toString();
        action[2] = y.toString();
        MainWindowController.outputStream.writeObject(action);
        updater.updateCanvas(action);
    }
    
    @FXML
    public void draged(MouseEvent event) throws IOException
    {
        Double x = event.getX();
        Double y = event.getY();
        Double size = 5.0;
        String action[] = new String[5];
        action[0] = "OnMouseDragged";
        action[1] = x.toString();
        action[2] = y.toString();
        action[3] = size.toString();
        action[4] = colorPicker.getValue().toString();
        MainWindowController.outputStream.writeObject(action);
        updater.updateCanvas(action);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
        
        updater = new UpdaterCanvas(canvas);

        
        for(String [] action: SettingsController.getHystory())
            updater.updateCanvas(action);
   }    


     
    
}
