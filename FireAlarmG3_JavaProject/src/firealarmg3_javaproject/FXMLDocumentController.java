/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package firealarmg3_javaproject;

import eu.hansolo.medusa.Gauge;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import communication.SerialProtocol;
import firealarmg3_javaproject.Client;


public class FXMLDocumentController implements Initializable {
    
    Client client =  new Client();
    boolean flag=true;
    
    public FXMLDocumentController() throws IOException {  
    }
    @FXML
    private Label label;
    @FXML
    private Gauge thermometer;
    @FXML
    private AnchorPane Alert;
    
    @FXML
    private void handleButtonHelp(ActionEvent event) {
        client.fire.stop();
    }
    @FXML
    private void handleButtonStop(ActionEvent event) {
        
        Alert.setVisible(false);
        client.fire.stop();
        System.out.println("STOP IS CLICKED");
       
    }
    @FXML
    private void handleButtonHistory(ActionEvent event) {
      client.ps.println("2");
      
           
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
        SerialProtocol newConnection = new SerialProtocol();
        newConnection.arduinoConnection();
        newConnection.receive();
         client.start();
        
             Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
               while(true){
                     System.out.println("entered thread 2");
                 try {
                    Thread.sleep(1000);
                    client.fire.fireOccurence();
                    thermometer.setValue(client.fire.getTemp());
                    if(client.fire.getTemp()>25 && flag==true){
                         Alert.setVisible(true);
                         flag=false;
                    }else if(client.fire.getTemp()<25 && flag==false){
                        flag=true;
                    }

                } catch (InterruptedException e) {}
            }
        }
            
            
        });
        t3.start();
        
    

   
    }    
    
}
