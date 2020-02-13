/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReclamationUIController implements Initializable {

    @FXML
    private TextField txtTitreRec;
    @FXML
    private ComboBox<String> cbTypeRec;
    @FXML
    private TextArea txtMsgRec;
    @FXML
    private Button btnEnv;
    
    ObservableList<String> listetype = FXCollections.observableArrayList("message1","message2","message3","message3");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbTypeRec.setItems(listetype);
        
    }    
    
}
