/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RepondreMailController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private TextArea body;
    @FXML
    private TextField subject;
    @FXML
    private Button Send;
    @FXML
    private Button Clear;
    @FXML
    private Button Afficher;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Send(ActionEvent event) {
    }

    @FXML
    private void Clear(ActionEvent event) {
    }

    @FXML
    private void Afficher(ActionEvent event) {
    }

    @FXML
    private void Retour(ActionEvent event) {
    }
    
}
