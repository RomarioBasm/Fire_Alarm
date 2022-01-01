/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package firealarmg3_javaproject;

import eu.hansolo.medusa.Gauge;
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

/**
 *
 * @author Romario
 */
public class FXMLDocumentController implements Initializable {
    
  
    @FXML
    private Gauge thermometer;
    @FXML
    private Button help;
    @FXML
    private Button history;
    @FXML
    private Label label;
    @FXML
    private AnchorPane A2;
    
    @FXML
    private AnchorPane A1;
    @FXML
    private Tab tab1;
    
    @FXML
    private Tab tab2;
    @FXML
    private TabPane tabpane;

    
    
   
    

    private void handleButtonAction(ActionEvent event) {
       
        thermometer.setValue(60.00);
        
    }
    @FXML
    private void handleHelpAction(ActionEvent event) {
       
        thermometer.setValue(60.00);
        
    }
    @FXML
    private void handleHistoryAction(ActionEvent event) {
       
 
        this.tabpane.getSelectionModel().select(this.tab2);
        thermometer.setValue(30.00);
        
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
