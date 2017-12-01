package com.suai.paintclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {
    
    private Socket socket;
    
    private  InetAddress IP;
    
    private int port = 7124;
    
    public static ObjectOutputStream outputStream ;
    
    public static ObjectInputStream inputStream ;
    
    private String[] suka;
    
    public static Stage stage;
    
    @FXML
    private Label label;
    
    @FXML
    private TableColumn clmRooms;
    
    @FXML
    private TableView tblRooms;
    
    @FXML
    private Button btnCreate;
    
    @FXML
    void createRoom(ActionEvent actionEvent)
    {
        try
        {
            Stage stageS = new Stage();
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Settings.fxml"));
            Parent root = loader.load();
            
            stageS.setTitle("Настройки комнаты");
            stageS.setResizable(false); //
            stageS.setScene(new Scene(root));
            stageS.initModality(Modality.WINDOW_MODAL); 
            //actionEvent.
            stageS.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stageS.show();
        }
        catch(IOException e)
        {
             System.out.println("MainWindowController.createRoom() " + e.getMessage());
        }
    }
    
    @FXML
    private void join()
    {
       try
        {
            String[] require = new String[2]; 
            require[0] = "j";
            require[1] = suka[0];
            
            outputStream.writeObject(require);
            
            try 
            {
//                SettingsController.history
             SettingsController.history = (LinkedList<String[]>) inputStream.readObject();
             
        } catch (IOException ex) {
            System.out.println("CanvasController" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("CanvasController" + ex.getMessage());
        }
         catch (Exception ex) {
            System.out.println("CanvasController" + ex.getMessage());
        }
                    
            Stage stage = new Stage();
            FXMLLoader loader  = new FXMLLoader();
            System.out.println("Suka");
//            loader.setLocation(getClass().getResource("/fxml/Canvas.fxml"));
            loader.setLocation(getClass().getResource("/fxml/FXML.fxml"));
            
            Parent root = loader.load();

            //отправляем серверу сообщение

            System.out.println("Posle load");
            stage.setTitle("Multiuser Paint");
            stage.setResizable(false); //
            stage.setScene(new Scene(root));
            MainWindowController.stage.close();
            stage.show();
            
        }
        catch(IOException e)
        {
             System.out.println("14888 " + e.getMessage() + "ЫЫЫЫЫЫ");
        }
    }
            
    public static ObjectInputStream getIn()
    {
        return inputStream;
    }
    public static ObjectOutputStream getOut()
    {
        return outputStream;
    }

    
    public void initialize(URL url, ResourceBundle rb) {
        
        connect();
       // updateListRooms();
        //получение и вывод списка комнат
    }
    
    private boolean connect()
    {
        try 
        {
            IP = InetAddress.getByName("localhost");
            socket = new Socket(IP, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream   = new ObjectInputStream(socket.getInputStream());
            suka = (String[])inputStream.readObject();
            return true;
        } catch (IOException ex) 
        {
            System.out.println("MainWindowController.connect() " + ex.getMessage());
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private void updateListRooms()
    {
        try 
        {
            String[] rooms = (String[])inputStream.readObject();
            //сделать цикл и запустить его в потоке, для постоянного обновления комнат
        } catch (IOException ex) 
        {
            System.out.println("MainWindowController.udateListRooms() " + ex.getMessage());
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("MainWindowController.udateListRooms() " + ex.getMessage());
        }
        
        
    }
}
